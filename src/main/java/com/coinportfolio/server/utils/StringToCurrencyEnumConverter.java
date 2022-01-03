package com.coinportfolio.server.utils;

import com.coinportfolio.server.enums.CurrenciesEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCurrencyEnumConverter implements Converter<String, CurrenciesEnum> {
    @Override
    public CurrenciesEnum convert(String value) {
        try{
            return CurrenciesEnum.valueOf(value);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Enum does not exist for: " + "value");
        }
    }
}
