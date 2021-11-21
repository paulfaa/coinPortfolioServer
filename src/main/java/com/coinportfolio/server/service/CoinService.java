package com.coinportfolio.server.service;

import com.coinportfolio.server.AllCoins;
import com.coinportfolio.server.enums.CurrenciesEnum;
import com.coinportfolio.server.models.Coin;
import com.coinportfolio.server.models.Rate;
import com.coinportfolio.server.utils.CoinNameToCoinmarketId;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

@Service
public class CoinService {

    @Autowired
    private static RestTemplate restTemplate;

    @Autowired
    private AllCoins allCoins;

    private String testUrl = "sandbox-api.coinmarketcap.com";
    private String prodUrl = "pro-api.coinmarketcap.com";
    private String testApiKey = "b54bcf4d-1bca-4e8e-9a24-22ff2c3d462c";

    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);

    private final WebClient localApiClient;

    @Autowired
    public CoinService(WebClient localApiClient) {
        this.localApiClient = localApiClient;
    }

    //private HttpHeaders request;
    //request.setHeader(HttpHeaders.ACCEPT, "application/json");
    //request.addHeader("X-CMC_PRO_API_KEY", testApiKey);

    public void RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    //need to write method to parse json

    public Rate processRequestParams(Map<String, CurrenciesEnum> query) throws JsonProcessingException {
        String requestedCoin = query.keySet().stream().findFirst().get();
        int coinId = CoinNameToCoinmarketId.convertNameToInt(requestedCoin);
        if (allCoins.checkIfListContainsCoin(requestedCoin)){
            allCoins.addCoin(new Coin(coinId, requestedCoin));
        }
        CurrenciesEnum currency = query.values().stream().findFirst().get();
        HashMap<CurrenciesEnum, Rate> rates = allCoins.getCoin("coinName").getCurrencyValues();
        if (rates.containsKey(currency) && LocalDateTime.now().getHour() <= rates.get(currency).getLocalDateTime().getHour() ){
            //if recent rate for this currency already exists, return it
            return rates.get(currency);
        }
        Rate rate = new Rate(currency, CoinService.getCoinmarketRateForCoin(coinId), LocalDateTime.now());
        //update with new rate
        allCoins.getCoin("coinName").setCurrencyValues(currency, rate);
        return rate;
    }

    public static long getCoinmarketRateForCoin(String coinName) throws JsonProcessingException {
        int coinId = CoinNameToCoinmarketId.convertNameToInt(coinName.toUpperCase(Locale.ROOT));
        return Long.parseLong(Objects.requireNonNull(restTemplate.getForObject("https://sandbox-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?id=" + coinId,
                String.class)));
    }

    public static long getCoinmarketRateForCoin(int coinId) throws JsonProcessingException {
        return Long.parseLong(Objects.requireNonNull(restTemplate.getForObject("https://sandbox-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?id=" + coinId,
                String.class)));
    }

    public String getFromUrl() throws JsonProcessingException {
        return restTemplate.getForObject("https://sandbox-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?id=1",
                String.class);
    }

    public int getPriceFromResponse(String apiResponse){
        //might be easier to just define request better
        //apiResponse =
        return 0;
    }
}