package com.coinportfolio.server;

import com.coinportfolio.server.utils.CoinNameToCoinmarketId;
import com.coinportfolio.server.utils.CoinmarketApi;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    private CoinmarketApi coinmarketApi;

    @Test
    public void testGetCoinmarketRateForCoin() throws JsonProcessingException {
        assertEquals("1234", coinmarketApi.getCoinmarketRateForCoin("bitcoin"));
    }

}
