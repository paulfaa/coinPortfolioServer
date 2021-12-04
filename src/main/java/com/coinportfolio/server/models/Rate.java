package com.coinportfolio.server.models;

import com.coinportfolio.server.enums.CurrenciesEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class Rate {
    CurrenciesEnum currency; //might not be needed
    private BigDecimal value;
    private LocalDateTime localDateTime;

    public Rate(CurrenciesEnum currency, BigDecimal value, LocalDateTime localDateTime) {
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

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(int BigDecimal) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Rate)) {
            return false;
        }
        Rate other = (Rate) obj;
        return (currency == other.currency
                && value.compareTo(other.getValue()) == 0 //ignore this while using testApi otherwise always fails
                && localDateTime.getHour() == other.getLocalDateTime().getHour());
    }
}
