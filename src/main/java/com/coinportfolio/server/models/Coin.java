package com.coinportfolio.server.models;

import com.coinportfolio.server.enums.CurrenciesEnum;

import java.math.BigDecimal;
import java.util.HashMap;

public class Coin {

    private int id;
    private String name;
    // store value for this particular coin in different currencies
    private HashMap<CurrenciesEnum, Rate> currencyValues = new HashMap<CurrenciesEnum, Rate>();

    public Coin(int id, String name) { //missing currencyValues in constructor
        this.id = id;
        this.name = name;
        this.currencyValues = currencyValues;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(CurrenciesEnum currencyTicker, Rate rate){
        currencyValues.put(currencyTicker, rate);
    }

    public BigDecimal getValue(CurrenciesEnum currencyTicker){
        return currencyValues.get(currencyTicker).getValue();
    }

    public HashMap<CurrenciesEnum, Rate> getCurrencyValues(){
        return currencyValues;
    }

    public void setCurrencyValues(CurrenciesEnum currenciesEnum, Rate rate){
        currencyValues.put(currenciesEnum, rate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coin coin = (Coin) o;
        return name.equals(coin.name);
    }
}
