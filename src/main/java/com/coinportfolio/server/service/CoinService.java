package com.coinportfolio.server.service;

import com.coinportfolio.server.AllCoins;
import com.coinportfolio.server.enums.CurrenciesEnum;
import com.coinportfolio.server.models.Coin;
import com.coinportfolio.server.models.Rate;
import com.coinportfolio.server.utils.CoinNameToCoinmarketId;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

@Service
public class CoinService {

    private static RestTemplate restTemplate = new RestTemplateBuilder(rt-> rt.getInterceptors().add((request, body, execution) -> {
        request.getHeaders().add("X-CMC_PRO_API_KEY", "047f0335-8f37-4cb3-a596-222dac0321a6");
        return execution.execute(request, body);
    })).build();

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

    public void RestService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public Rate processRequestParams(Map<String, CurrenciesEnum> query) throws JSONException {
        String requestedCoin = query.keySet().stream().findFirst().get();
        int coinId = CoinNameToCoinmarketId.convertNameToInt(requestedCoin);
        if (!allCoins.checkIfListContainsCoin(requestedCoin)){
            allCoins.addCoin(new Coin(coinId, requestedCoin));
        }
        CurrenciesEnum currency = query.values().stream().findFirst().get();
        Coin c = allCoins.getCoin(requestedCoin);
        System.out.println(c);
        HashMap<CurrenciesEnum, Rate> rates = allCoins.getCoin(requestedCoin).getCurrencyValues(); //need null check here
        if (rates.containsKey(currency) && LocalDateTime.now().getHour() <= rates.get(currency).getLocalDateTime().getHour() ){
            //if recent rate for this currency already exists, return it
            return rates.get(currency);
        }
        Rate rate = new Rate(currency, getCoinmarketRateForCoin(coinId), LocalDateTime.now());
        //update with new rate
        allCoins.getCoin("coinName").setCurrencyValues(currency, rate);
        return rate;
    }

    public static BigDecimal getCoinmarketRateForCoin(String coinName) throws JSONException {
        int coinId = CoinNameToCoinmarketId.convertNameToInt(coinName.toUpperCase(Locale.ROOT));
        String response = Objects.requireNonNull(restTemplate.getForObject("https://sandbox-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?id=" + coinId,
                String.class));
        return getPriceFromResponse(response);
    }

    public static BigDecimal getCoinmarketRateForCoin(int coinId) throws JSONException {
        String response = Objects.requireNonNull(restTemplate.getForObject("https://sandbox-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?id=" + coinId,
                String.class));
        return getPriceFromResponse(response);
    }

    public String getFromUrl() {
        return restTemplate.getForObject("https://sandbox-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?id=1",
                String.class);
    }

    //method to parse json
    public static BigDecimal getPriceFromResponse(String apiResponse) throws JSONException {
        JSONObject json = new JSONObject(apiResponse);
        String price =
                 json.getJSONObject("data")
                .getJSONObject("1")
                .getJSONObject("quote")
                .getJSONObject("USD") //should use wildcard here or better search directly for quote objecy and get price of first child
                .getString("price");
        return new BigDecimal(price);
    }
}