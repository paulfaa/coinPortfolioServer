package com.coinportfolio.server;

import com.coinportfolio.server.utils.CoinmarketApi;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CoinmarketApiTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    private CoinmarketApi coinmarketApi;

    @Test
    public void testGetCoinmarketRateForCoin(){
        assertEquals(coinmarketApi.getCoinmarketRateForCoin("bitcoin"), "1234");
    }

}
