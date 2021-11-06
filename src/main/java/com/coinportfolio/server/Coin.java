package com.coinportfolio.server;

import java.util.HashMap;

public class Coin {

    private int id;
    private String name;
    private HashMap<CurrenciesEnum, Integer> currencyValues = new HashMap<CurrenciesEnum, Integer>();

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

    public void addOrUpdateValue(CurrenciesEnum currencyTicker, int rate){
        currencyValues.put(currencyTicker, rate);
    }

    public int getValue(CurrenciesEnum currencyTicker){
        return currencyValues.get(currencyTicker);
    }

}
