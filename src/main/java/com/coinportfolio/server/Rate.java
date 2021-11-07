package com.coinportfolio.server;

import com.coinportfolio.server.enums.CurrenciesEnum;

import java.time.LocalDateTime;
import java.util.Date;

public class Rate {
    CurrenciesEnum currency; //might not be needed
    private int value;
    private LocalDateTime localDateTime;

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setDate(LocalDateTime date) {
        this.localDateTime = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
