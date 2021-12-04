package com.coinportfolio.server.service;

import com.coinportfolio.server.AllCoins;
import com.coinportfolio.server.enums.CurrenciesEnum;
import com.coinportfolio.server.models.Coin;
import com.coinportfolio.server.models.Rate;
import com.coinportfolio.server.utils.CoinNameToCoinmarketId;
import com.coinportfolio.server.utils.RestTemplateResponseErrorHandler;
import org.json.JSONException;
import org.json.JSONObject;
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


    //public void RestService(RestTemplateBuilder restTemplateBuilder) {
    //    restTemplate = restTemplateBuilder.build();
    //}

    public Rate processRequestParams(Map<String, CurrenciesEnum> query) throws JSONException {
        String requestedCoin = query.keySet().stream().findFirst().get();
        int coinId = CoinNameToCoinmarketId.convertNameToInt(requestedCoin);
        if (!allCoins.checkIfListContainsCoin(requestedCoin)){ //should search for coins using coinmarket id wherever possible
            allCoins.addCoin(new Coin(coinId, requestedCoin)); //can convert id to string using enum
        }
        CurrenciesEnum currency = query.values().stream().findFirst().get();
        Coin c = allCoins.getCoin(requestedCoin);
        HashMap<CurrenciesEnum, Rate> rates = allCoins.getCoin(requestedCoin).getCurrencyValues();
        if (rates.containsKey(currency) && LocalDateTime.now().getHour() <= rates.get(currency).getLocalDateTime().getHour() ){
            //if recent rate for this currency already exists, return it
            return rates.get(currency);
        }
        Rate rate = new Rate(currency, getCoinmarketRateForCoin(coinId, currency), LocalDateTime.now());
        //update with new rate
        c.setCurrencyValues(currency, rate);
        return rate;
    }

    public static BigDecimal getCoinmarketRateForCoin(String coinName, CurrenciesEnum currency) throws JSONException {
        int coinId = CoinNameToCoinmarketId.convertNameToInt(coinName.toUpperCase(Locale.ROOT));
        String response = Objects.requireNonNull(restTemplate.getForObject(DEFAULT_QUOTE_REQUEST + coinId + "&convert=" + currency.toString(),
                String.class));
        return getPriceFromResponse(response, currency);
    }

    public static BigDecimal getCoinmarketRateForCoin(int coinId, CurrenciesEnum currency) throws JSONException {
        String response = Objects.requireNonNull(restTemplate.getForObject(DEFAULT_QUOTE_REQUEST + coinId + "&convert=" + currency.toString(),
                String.class));
        return getPriceFromResponse(response, currency);
    }

    public String getFromUrl() {
        return restTemplate.getForObject(DEFAULT_QUOTE_REQUEST,
                String.class);
    }

    public static BigDecimal getPriceFromResponse(String apiResponse, CurrenciesEnum currency) throws JSONException {
        JSONObject object = new JSONObject(apiResponse);
        String price =
                 object.getJSONObject("data")
                .getJSONObject("0") //changes between 1 and 0? need to fix
                .getJSONObject("quote")
                .getJSONObject(currency.toString().toUpperCase())
                .getString("price");
        return new BigDecimal(price);
    }
}