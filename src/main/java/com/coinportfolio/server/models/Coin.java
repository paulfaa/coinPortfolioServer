package com.coinportfolio.server.models;

import com.coinportfolio.server.enums.CoinIdEnum;
import com.coinportfolio.server.enums.CurrenciesEnum;
import java.math.BigDecimal;
import java.util.HashMap;

public class Coin {
    private CoinIdEnum id;
    private String name;

    private HashMap<CurrenciesEnum, Value> currencyValues; // store value for this particular coin in different currencies

    public Coin(CoinIdEnum id, String name) { //missing currencyValues in constructor
        this.id = id;
        this.name = name;
        this.currencyValues = new HashMap<CurrenciesEnum, Value>();
    }

    public CoinIdEnum getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(CurrenciesEnum currencyTicker, Value value){
        currencyValues.put(currencyTicker, value);
    }

    public BigDecimal getValue(CurrenciesEnum currencyTicker){
        try{
            return currencyValues.get(currencyTicker).getValue();
        }
        catch (Exception e){
            throw new NullPointerException("Coin does not hold a value for " + currencyTicker.toString() + e);
        }
    }

    public HashMap<CurrenciesEnum, Value> getCurrencyValues(){
        return currencyValues;
    }

    public void setCurrencyValues(CurrenciesEnum currenciesEnum, Value value){
        currencyValues.put(currenciesEnum, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coin coin = (Coin) o;
        return name.equals(coin.name);
    }
}
