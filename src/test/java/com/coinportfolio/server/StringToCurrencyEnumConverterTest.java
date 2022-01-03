package com.coinportfolio.server;

import com.coinportfolio.server.enums.CurrenciesEnum;
import com.coinportfolio.server.utils.StringToCurrencyEnumConverter;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringToCurrencyEnumConverterTest {

    private StringToCurrencyEnumConverter converter = new StringToCurrencyEnumConverter();

    @Test
    public void testConvertValidString(){
        converter = new StringToCurrencyEnumConverter();
        CurrenciesEnum validResponse = converter.convert("EUR");
        Assert.assertEquals(CurrenciesEnum.EUR, validResponse);
    }

    @Test
    public void testConvertInvalidString(){
        assertThrows(IllegalArgumentException.class,
                ()->{
                    converter.convert("invalid");
                });
    }
}
