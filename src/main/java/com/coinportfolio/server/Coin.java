package com.coinportfolio.server;

import java.util.HashMap;

public class Coin {

    private int id;
    private String name;
    // store value for this particular coin in different currencies
    private HashMap<CurrenciesEnum, Rate> currencyValues = new HashMap<CurrenciesEnum, Rate>();

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

}
