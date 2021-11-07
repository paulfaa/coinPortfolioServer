package com.coinportfolio.server.utils;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Locale;

@Service
public class CoinmarketApi {

    private RestTemplate restTemplate;
    private CoinNameToCoinmarketId coinNameToCoinmarketId;
    private String testUrl = "sandbox-api.coinmarketcap.com";
    private String url = "pro-api.coinmarketcap.com";
    private String testApiKey = "b54bcf4d-1bca-4e8e-9a24-22ff2c3d462c";

    //private HttpHeaders request;
    //request.setHeader(HttpHeaders.ACCEPT, "application/json");
    //request.addHeader("X-CMC_PRO_API_KEY", testApiKey);

    public void RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getCoinmarketRateForCoin(String coinName) {
        int coinId = coinNameToCoinmarketId.convertNameToInt(coinName.toUpperCase(Locale.ROOT));
        url = url + "/v1/cryptocurrency/quotes/latest?id=" + coinId;
        return this.restTemplate.getForObject(url, String.class);
    }
}