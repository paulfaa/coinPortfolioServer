package com.coinportfolio.server.models;

import com.coinportfolio.server.enums.CurrenciesEnum;

import java.util.HashMap;

public class Coin {

    private int id;
    private String name;
    // store value for this particular coin in different currencies
    public HashMap<CurrenciesEnum, Rate> currencyValues = new HashMap<CurrenciesEnum, Rate>();

    public Coin(int id, String name) {
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

    public int getValue(CurrenciesEnum currencyTicker){
        return currencyValues.get(currencyTicker).getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coin coin = (Coin) o;
        return name.equals(coin.name);
    }

}
