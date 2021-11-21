package com.coinportfolio.server;

import com.coinportfolio.server.service.CoinService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CoinServiceTest {

    @LocalServerPort
    private int port;

    @Autowired
    private CoinService coinService;

    @Test
    public void testGetCoinmarketRateForCoin() throws JsonProcessingException {
        assertEquals("1234", coinService.getCoinmarketRateForCoin("bitcoin"));
    }

}
