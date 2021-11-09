package com.coinportfolio.server.utils;

import com.coinportfolio.server.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.Locale;

@Service
public class CoinmarketApi {

    @Autowired
    private RestTemplate restTemplate;

    private String testUrl = "sandbox-api.coinmarketcap.com";
    private String prodUrl = "pro-api.coinmarketcap.com";
    private String testApiKey = "b54bcf4d-1bca-4e8e-9a24-22ff2c3d462c";

    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);

    private final WebClient localApiClient;

    @Autowired
    public CoinmarketApi(WebClient localApiClient) {
        this.localApiClient = localApiClient;
    }

    //private HttpHeaders request;
    //request.setHeader(HttpHeaders.ACCEPT, "application/json");
    //request.addHeader("X-CMC_PRO_API_KEY", testApiKey);

    public void RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getCoinmarketRateForCoin(String coinName) {
        int coinId = CoinNameToCoinmarketId.convertNameToInt(coinName.toUpperCase(Locale.ROOT));
        String response =  localApiClient
                .get()
                .uri("/v1/cryptocurrency/quotes/latest?id=" + coinId)
                .retrieve()
                .bodyToMono(String.class)
                .block(REQUEST_TIMEOUT);
        return response; //returning null
    }
}