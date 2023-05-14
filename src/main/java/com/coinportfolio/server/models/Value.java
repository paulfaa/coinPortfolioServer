package com.coinportfolio.server.models;

import com.coinportfolio.server.enums.CoinIdEnum;
import com.coinportfolio.server.enums.CurrencyEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Value {

    private CoinIdEnum id;
    //private string name;
    private CurrencyEnum currency;
    private BigDecimal value;
    private LocalDateTime updateDate;

    public Value(CurrencyEnum currency, BigDecimal value, LocalDateTime localDateTime) {
        this.currency = currency;
        this.value = value;
        this.updateDate = localDateTime;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime date) {
        this.updateDate = date;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Value)) {
            return false;
        }
        Value other = (Value) obj;
        return (currency == other.currency
                && value.compareTo(other.getValue()) == 0 //ignore this while using testApi otherwise always fails
                && other.getUpdateDate().isBefore(updateDate.plus(1, ChronoUnit.HOURS))
                && other.getUpdateDate().isAfter(updateDate.minus(1, ChronoUnit.HOURS)));
    }
}
