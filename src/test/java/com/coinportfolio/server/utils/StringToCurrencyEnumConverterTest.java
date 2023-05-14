package com.coinportfolio.server.utils;

import com.coinportfolio.server.enums.CurrencyEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringToCurrencyEnumConverterTest {

    private StringToCurrencyEnumConverter converter = new StringToCurrencyEnumConverter();

    @Test
    public void testConvertValidString(){
        converter = new StringToCurrencyEnumConverter();
        CurrencyEnum validResponse = converter.convert("EUR");
        Assertions.assertEquals(CurrencyEnum.EUR, validResponse);
    }

    @Test
    public void testConvertInvalidString(){
        Assertions.assertThrows(IllegalArgumentException.class,
                ()->{
                    converter.convert("invalid");
                });
    }
}
