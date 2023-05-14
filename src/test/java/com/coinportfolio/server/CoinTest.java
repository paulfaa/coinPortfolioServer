package com.coinportfolio.server;

import com.coinportfolio.server.enums.CoinIdEnum;
import com.coinportfolio.server.enums.CurrencyEnum;
import com.coinportfolio.server.models.Coin;
import com.coinportfolio.server.models.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoinTest {

    public Coin coin;
    public Value value;

    @BeforeEach
    public void setup(){
        coin = new Coin(CoinIdEnum.BITCOIN, "Bitcoin");
        value = new Value(CurrencyEnum.EUR, BigDecimal.valueOf(0.55), LocalDateTime.now());
    }

    @Test
    public void testGetValue(){
        //Arrange
        coin.setValue(CurrencyEnum.EUR, value);

        //Act
        BigDecimal value = coin.getValue(CurrencyEnum.EUR);

        //Assert
        Assertions.assertEquals(BigDecimal.valueOf(0.55), value);
    }

    @Test
    public void testGetNullValue() {
        //Assert
        assertThrows(NullPointerException.class,
                () -> {
                    coin.getValue(CurrencyEnum.EUR);
        });
    }

    @Test
    public void testSetValueOverwritesPrevious(){
        Assertions.assertEquals(0, coin.getCurrencyValues().size());
        coin.setValue(CurrencyEnum.EUR, value);
        Assertions.assertEquals(1, coin.getCurrencyValues().size());

        Value newValue = new Value(CurrencyEnum.EUR, BigDecimal.valueOf(4.56), LocalDateTime.now());
        coin.setValue(CurrencyEnum.EUR, newValue);
        Assertions.assertEquals(1, coin.getCurrencyValues().size());
    }

    @Test
    public void testEquals() { //ensure equals method only looks at coin name
        //Arrange
        Coin otherBitcoin = new Coin(CoinIdEnum.BITCOIN, "Bitcoin");
        Value otherValue = new Value(CurrencyEnum.EUR, BigDecimal.valueOf(4.56), LocalDateTime.now());
        otherBitcoin.setValue(CurrencyEnum.EUR, otherValue);
        Coin fakeCoin = new Coin(CoinIdEnum.DOGECOIN, "Dogecoin");

        //Assert
        Assertions.assertEquals(coin, otherBitcoin);
        Assertions.assertNotEquals(coin, fakeCoin);
    }
}
