package com.coinportfolio.server;

import com.coinportfolio.server.enums.CurrenciesEnum;
import com.coinportfolio.server.models.Coin;
import com.coinportfolio.server.models.Rate;
import com.coinportfolio.server.service.CoinService;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CoinServiceTest {

    @LocalServerPort
    private int port;

    @Autowired
    private CoinService coinService;

    @Autowired
    private AllCoins allCoins;

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
        assertEquals(0, allCoins.getLength());

        //Act
        request.put("Bitcoin", CurrenciesEnum.USD);
        Rate actualRate = coinService.processRequestParams(request);

        //Assert
        assertEquals(1, allCoins.getLength());
        assertTrue(targetRate.getValue() != null);
        assertTrue(targetRate.getValue().getClass() == BigDecimal.class);
        //value of response changes each time for testAPI server, just assert rate is a bigDecimal
        //assertTrue(targetRate.equals(actualRate));
    }

    @Test
    public void testServiceReadsFromAllCoins() throws JSONException {
        // Make sure method reads from AllCoins if it already contains valid data for that coin + currency
        // Arrange
        BigDecimal coinValue = BigDecimal.valueOf(50000);
        Coin coin = new Coin(1, "Bitcoin");
        Rate rate = new Rate(CurrenciesEnum.USD, coinValue, LocalDateTime.now());
        coin.setValue(CurrenciesEnum.USD, rate);
        allCoins.addCoin(coin);
        Map<String, CurrenciesEnum> request = new HashMap<>();
        request.put("Bitcoin", CurrenciesEnum.USD);
        assertEquals(1, allCoins.getLength());

        //Act
        Rate actualRate = coinService.processRequestParams(request);

        // Assert
        assertEquals(1, allCoins.getLength());
        assertTrue(coinValue.equals(actualRate.getValue()));
    }

    @Test
    public void test404() {
        Exception exception = assertThrows(IOException.class, () -> {
            coinService.getCoinmarketRateForCoin("doesNotExist", CurrenciesEnum.USD);
        });
        String expectedMessage = "404 error";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
