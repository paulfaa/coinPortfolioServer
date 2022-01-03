package com.coinportfolio.server.service;

import com.coinportfolio.server.AllCoins;
import com.coinportfolio.server.enums.CurrenciesEnum;
import com.coinportfolio.server.exceptions.GetRateException;
import com.coinportfolio.server.exceptions.ResponseJsonException;
import com.coinportfolio.server.models.Coin;
import com.coinportfolio.server.models.Rate;
import com.coinportfolio.server.utils.CoinNameToCoinmarketId;
import com.coinportfolio.server.utils.RestTemplateResponseErrorHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CoinService {

    private static RestTemplate restTemplate = new RestTemplateBuilder(rt-> rt.getInterceptors().add((request, body, execution) -> {
        request.getHeaders().add("X-CMC_PRO_API_KEY", "047f0335-8f37-4cb3-a596-222dac0321a6");
        return execution.execute(request, body);
    })).errorHandler(new RestTemplateResponseErrorHandler()).build();

    @Autowired
    private AllCoins allCoins;

    @Autowired
    public CoinService(WebClient localApiClient) {
        this.localApiClient = localApiClient;
    }

    private final WebClient localApiClient;
    private String testUrl = "sandbox-api.coinmarketcap.com";
    private String prodUrl = "pro-api.coinmarketcap.com";
    private String testApiKey = "b54bcf4d-1bca-4e8e-9a24-22ff2c3d462c";

    //private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);
    private static final String DEFAULT_QUOTE_REQUEST = "https://sandbox-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?id=";
    private static final int DEFAULT_REQUEST_COOLDOWN = 3600;

    public Rate processRequestParams(Map<String, CurrenciesEnum> query) throws GetRateException {
        String requestedCoin = query.keySet().stream().findFirst().get();
        int coinId = CoinNameToCoinmarketId.convertNameToInt(requestedCoin);
        if (!allCoins.checkIfListContainsCoin(coinId)){
            allCoins.addCoin(new Coin(coinId, CoinNameToCoinmarketId.convertIdToName(coinId)));
        }
        CurrenciesEnum currency = query.values().stream().findFirst().get();
        Coin c = allCoins.getCoin(coinId);
        HashMap<CurrenciesEnum, Rate> rates = c.getCurrencyValues();
        //if recent rate for this currency already exists, return it
        if (rates.containsKey(currency) && LocalDateTime.now().getHour() <= rates.get(currency).getLocalDateTime().getHour() ){
            return rates.get(currency);
        }
        //otherwise, make api call to get rate
        try{
            Rate rate = new Rate(currency, getCoinmarketRateForCoin(coinId, currency), LocalDateTime.now());
            c.setCurrencyValues(currency, rate);
            return rate;
        }
        catch (Exception e){
            throw new GetRateException("Error in getting coinMarketCap rate for " + requestedCoin, e);
        }
    }

    public static BigDecimal getCoinmarketRateForCoin(String coinName, CurrenciesEnum currency) throws ResponseJsonException {
        int coinId = CoinNameToCoinmarketId.convertNameToInt(coinName.toUpperCase(Locale.ROOT));
        String response = Objects.requireNonNull(restTemplate.getForObject(DEFAULT_QUOTE_REQUEST + coinId + "&convert=" + currency.toString(),
                String.class));
        return getPriceFromResponse(response);
    }

    public static BigDecimal getCoinmarketRateForCoin(int coinId, CurrenciesEnum currency) throws ResponseJsonException {
        String response = Objects.requireNonNull(restTemplate.getForObject(DEFAULT_QUOTE_REQUEST + coinId + "&convert=" + currency.toString(),
                String.class));
        return getPriceFromResponse(response);
    }

    public String getFromUrl() {
        return restTemplate.getForObject("https://sandbox-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?id=1",
                String.class);
    }

    public static BigDecimal getPriceFromResponse(String apiResponse) throws ResponseJsonException {
        try {
            ObjectMapper mapper = new JsonMapper();
            JsonNode node = mapper.readTree(apiResponse);
            BigDecimal price = new BigDecimal(node.findValue("price").toString());
            return price;
        }
        catch (Exception e){
            throw new ResponseJsonException("Error reading response JSON. Details: ", e);
        }
    }
}