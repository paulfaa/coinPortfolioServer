package com.coinportfolio.server;

import com.coinportfolio.server.enums.CurrenciesEnum;
import com.coinportfolio.server.models.Rate;
import com.coinportfolio.server.service.CoinService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.math.BigDecimal;
import java.math.BigInteger;
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
    public void testGetCoinmarketRateForCoin() throws JSONException {
        //assertEquals(BigDecimal.valueOf(0.6763088220435858), coinService.getCoinmarketRateForCoin("bitcoin"));
        //value of response changes each time for testAPI server, just assert response is a bigDecimal
        // Act
        BigDecimal response = coinService.getCoinmarketRateForCoin("bitcoin", CurrenciesEnum.USD);

        Assertions.assertNotNull(response);
        assertTrue(response.getClass() == BigDecimal.class);
    }

    @Test
    public void processRequestParams() throws JSONException {
        //Arrange
        Map<String, CurrenciesEnum> request = new HashMap<>();
        Rate targetRate = new Rate(CurrenciesEnum.USD, BigDecimal.valueOf(0123), LocalDateTime.now());

        //Act
        request.put("Bitcoin", CurrenciesEnum.USD);
        Rate actualRate = coinService.processRequestParams(request);

        //Assert
        assertTrue(targetRate.equals(actualRate)); //request works, just need function to format response properly
    }
}
