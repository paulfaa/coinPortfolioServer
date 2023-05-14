package com.coinportfolio.server.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CurrencyEnum {
    USD,
    EUR,
    AUD,
    JPY;

    @JsonCreator
    public static CurrencyEnum getEnumFromString(String value) {
        for (CurrencyEnum item : CurrencyEnum.values()) {
            if (value.toUpperCase().equals(item.toString())) {
                return item;
            }
        }
        return null;
    }
}
