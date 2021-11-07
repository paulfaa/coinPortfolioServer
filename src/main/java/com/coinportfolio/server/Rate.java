package com.coinportfolio.server;

import com.coinportfolio.server.enums.CurrenciesEnum;

import java.util.Date;

public class Rate {
    private Date date;
    private int value;
    CurrenciesEnum currency;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
