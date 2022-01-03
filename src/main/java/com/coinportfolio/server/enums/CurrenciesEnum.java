package com.coinportfolio.server.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CurrenciesEnum {
    USD,
    EUR,
    AUD,
    JPY;

    @JsonCreator
    public static CurrenciesEnum of(Integer value) {
        if (null == value) {
            return null;
        }

        for (CurrenciesEnum item : CurrenciesEnum.values()) {
            if (value.toString().equals(item.toString())) {
                return item;
            }
        }
        return null;
    }
}
