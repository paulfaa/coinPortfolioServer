package com.coinportfolio.server.models;

import com.coinportfolio.server.enums.CurrenciesEnum;

import java.time.LocalDateTime;
import java.util.Date;

public class Rate {
    CurrenciesEnum currency; //might not be needed
    private long value;
    private LocalDateTime localDateTime;

    public Rate(CurrenciesEnum currency, long value, LocalDateTime localDateTime) {
        this.currency = currency;
        this.value = value;
        this.localDateTime = localDateTime;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setDate(LocalDateTime date) {
        this.localDateTime = date;
    }

    public long getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
