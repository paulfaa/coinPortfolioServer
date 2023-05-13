package com.coinportfolio.server;

import com.coinportfolio.server.enums.CurrenciesEnum;
import com.coinportfolio.server.utils.StringToCurrencyEnumConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringToCurrencyEnumConverterTest {

    private StringToCurrencyEnumConverter converter = new StringToCurrencyEnumConverter();

    @Test
    public void testConvertValidString(){
        converter = new StringToCurrencyEnumConverter();
        CurrenciesEnum validResponse = converter.convert("EUR");
        Assertions.assertEquals(CurrenciesEnum.EUR, validResponse);
    }

    @Test
    public void testConvertInvalidString(){
        Assertions.assertThrows(IllegalArgumentException.class,
                ()->{
                    converter.convert("invalid");
                });
    }
}
