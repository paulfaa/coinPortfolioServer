package com.coinportfolio.server;

import com.coinportfolio.server.models.Coin;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@EnableAutoConfiguration
public class AllCoins {

    private ArrayList<Coin> allCoins;
    
    public boolean checkIfListContainsCoin(String coinName){
        for (Coin coin: allCoins) {
            if (coin.getName() == coinName){
                return true;
            }
        }
        return false;
    }

    public Coin getCoin(String coinName){
        for (Coin coin: allCoins) {
            if (coin.getName() == coinName){
                return coin;
            }
        }
        return null;
    }
}
