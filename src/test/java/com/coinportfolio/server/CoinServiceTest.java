package com.coinportfolio.server;

import com.coinportfolio.server.enums.CurrenciesEnum;
import com.coinportfolio.server.models.Rate;
import com.coinportfolio.server.service.CoinService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void processRequestParams() throws JsonProcessingException {
        //Arrange
        Map<String, CurrenciesEnum> request = new HashMap<>();
        Rate targetRate = new Rate(CurrenciesEnum.EUR, 0123, LocalDateTime.now());

        //Act
        request.put("Bitcoin", CurrenciesEnum.EUR);

        //Assert
        assertEquals(targetRate, coinService.processRequestParams(request));
    }

}
