package com.coinportfolio.server.utils;

import com.coinportfolio.server.enums.CurrencyEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCurrencyEnumConverter implements Converter<String, CurrencyEnum> {
    @Override
    public CurrencyEnum convert(String value) {
        try{
            return CurrencyEnum.valueOf(value);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Enum does not exist for: " + "value");
        }
    }
}
