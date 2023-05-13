package com.coinportfolio.server;

import com.coinportfolio.server.enums.CoinIdEnum;
import com.coinportfolio.server.enums.CurrenciesEnum;
import com.coinportfolio.server.models.Coin;
import com.coinportfolio.server.models.Rate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoinTest {

    public Coin coin;
    public Rate rate;

    @BeforeEach
    public void setup(){
        coin = new Coin(CoinIdEnum.BITCOIN, "Bitcoin");
        rate = new Rate(CurrenciesEnum.EUR, BigDecimal.valueOf(0.55), LocalDateTime.now());
    }

    @Test
    public void testGetValue(){
        //Arrange
        coin.setValue(CurrenciesEnum.EUR, rate);

        //Act
        BigDecimal value = coin.getValue(CurrenciesEnum.EUR);

        //Assert
        Assertions.assertEquals(BigDecimal.valueOf(0.55), value);
    }

    @Test
    public void testGetNullValue() {
        //Assert
        assertThrows(NullPointerException.class,
                () -> {
                    coin.getValue(CurrenciesEnum.EUR);
        });
    }

    @Test
    public void testSetValueOverwritesPrevious(){
        Assertions.assertEquals(0, coin.getCurrencyValues().size());
        coin.setValue(CurrenciesEnum.EUR, rate);
        Assertions.assertEquals(1, coin.getCurrencyValues().size());

        Rate newRate = new Rate(CurrenciesEnum.EUR, BigDecimal.valueOf(4.56), LocalDateTime.now());
        coin.setValue(CurrenciesEnum.EUR, newRate);
        Assertions.assertEquals(1, coin.getCurrencyValues().size());
    }

    @Test
    public void testEquals() { //ensure equals method only looks at coin name
        //Arrange
        Coin otherBitcoin = new Coin(CoinIdEnum.BITCOIN, "Bitcoin");
        Rate otherRate = new Rate(CurrenciesEnum.EUR, BigDecimal.valueOf(4.56), LocalDateTime.now());
        otherBitcoin.setValue(CurrenciesEnum.EUR, otherRate);
        Coin fakeCoin = new Coin(CoinIdEnum.DOGECOIN, "Dogecoin");

        //Assert
        Assertions.assertTrue(coin.equals(otherBitcoin));
        Assertions.assertNotEquals(coin, fakeCoin);
    }
}
