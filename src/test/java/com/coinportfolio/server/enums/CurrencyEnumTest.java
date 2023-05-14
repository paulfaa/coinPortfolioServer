package com.coinportfolio.server.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CurrencyEnumTest {
    @Test
    public void testGetEnumFromStringSuccess(){
        //Act
        CurrencyEnum result1 = CurrencyEnum.getEnumFromString("EUR");
        CurrencyEnum result2 = CurrencyEnum.getEnumFromString("eur");

        //Assert
        Assertions.assertEquals(CurrencyEnum.EUR, result1);
        Assertions.assertEquals(CurrencyEnum.EUR, result2);
    }

    @Test
    public void testGetEnumFromIdInvalidId(){
        //Act
        CurrencyEnum result1 = CurrencyEnum.getEnumFromString("invalid");
        CurrencyEnum result2 = CurrencyEnum.getEnumFromString("");

        //Assert
        Assertions.assertNull(result1);
        Assertions.assertNull(result2);
    }
}
