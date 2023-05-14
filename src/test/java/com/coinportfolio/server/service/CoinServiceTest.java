package com.coinportfolio.server.service;

import com.coinportfolio.server.AllCoins;
import com.coinportfolio.server.enums.CoinIdEnum;
import com.coinportfolio.server.enums.CurrencyEnum;
import com.coinportfolio.server.exceptions.GetRateException;
import com.coinportfolio.server.exceptions.ResponseJsonException;
import com.coinportfolio.server.models.Coin;
import com.coinportfolio.server.models.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CoinServiceTest {

    @LocalServerPort
    private int port;

    @Autowired
    private CoinService coinService;

    @Autowired
    private AllCoins allCoins;

    @BeforeEach
    public void setup(){
        allCoins.clearList();
    }

    @Test
    public void testGetCoinmarketRateForCoin() throws ResponseJsonException {
        // Act
        BigDecimal response = coinService.getCoinmarketRateForCoin("bitcoin", CurrencyEnum.USD);

        // Assert
        //value of response changes each time for testAPI server, just assert response is a bigDecimal
        assertNotNull(response);
        assertSame(response.getClass(), BigDecimal.class);
    }

    @Test
    public void processRequestParams() {
        //Arrange
        Map<String, CurrencyEnum> request = new HashMap<>();
        Value targetValue = new Value(CurrencyEnum.USD, BigDecimal.valueOf(0123), LocalDateTime.now());
        assertEquals(0, allCoins.getLength());

        //Act
        request.put("Bitcoin", CurrencyEnum.USD);
        Value actualValue = null;
        try {
            actualValue = coinService.processRequestParams(request);
        } catch (GetRateException e) {
            e.printStackTrace();
        }

        //Assert
        assertEquals(1, allCoins.getLength());
        assertTrue(actualValue.getValue() != null);
        assertTrue(actualValue.getValue().compareTo(BigDecimal.ZERO) > 0);
        assertSame(actualValue.getValue().getClass(), BigDecimal.class);
        //value of response changes each time for testAPI server, just assert rate is a valid bigDecimal
    }

    @Test
    public void testServiceReadsFromAllCoins() {
        // Make sure method reads from AllCoins if it already contains valid data for that coin + currency
        // Arrange
        BigDecimal coinValue = BigDecimal.valueOf(50000);
        Coin coin = new Coin(CoinIdEnum.BITCOIN, "Bitcoin");
        Value value = new Value(CurrencyEnum.USD, coinValue, LocalDateTime.now());
        coin.setValue(CurrencyEnum.USD, value);
        allCoins.addCoin(coin);
        Map<String, CurrencyEnum> request = new HashMap<>();
        request.put("Bitcoin", CurrencyEnum.USD);
        assertEquals(1, allCoins.getLength());

        //Act
        Value actualValue = null;
        try {
            actualValue = coinService.processRequestParams(request);
        } catch (GetRateException e) {
            e.printStackTrace();
        }

        // Assert
        assertEquals(1, allCoins.getLength());
        assertNotNull(actualValue.getValue());
        assertTrue(coinValue.equals(actualValue.getValue()));
    }

    @Test
    public void testGetPriceFromResponse(){
        //test with null response, assert exception is thrown
        String response = null;
        assertThrows(ResponseJsonException.class,
                ()->{
                    coinService.getPriceFromResponse(response);
                });
    }

    /*@Test
    public void testServiceIgnoresOutdatedRates() {
        // Make sure method ignores data in Allcoins if older than 1 hour
        // Arrange
        BigDecimal coinValue = BigDecimal.valueOf(50000);
        LocalDateTime oldDate = LocalDateTime.of(2021,01,01, 11, 11);
        Coin coin = new Coin(CoinIdEnum.BITCOIN, "Bitcoin");
        Value value = new Value(CurrencyEnum.USD, coinValue, oldDate);
        coin.setValue(CurrencyEnum.USD, value);
        allCoins.addCoin(coin);
        Map<String, CurrencyEnum> request = new HashMap<>();
        request.put("Bitcoin", CurrencyEnum.USD);
        assertEquals(1, allCoins.getLength());
        assertEquals(allCoins.getCoin(CoinIdEnum.BITCOIN).getCurrencyValues().get(CurrencyEnum.USD).getUpdateDate(), oldDate);

        //Act
        Value actualValue = null;
        try {
            actualValue = coinService.processRequestParams(request);
        } catch (GetRateException e) {
            e.printStackTrace();
        }

        // Assert
        assertEquals(1, allCoins.getLength());
        assertNotNull(actualValue.getValue());
        assertEquals(coinValue, actualValue.getValue());
    }*/

    @Test
    public void test404() {
        /*Exception exception = assertThrows(IOException.class, () -> {
            coinService.getCoinmarketRateForCoin("doesNotExist", CurrenciesEnum.USD);
        });
        String expectedMessage = "404 error";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));*/
    }
}
