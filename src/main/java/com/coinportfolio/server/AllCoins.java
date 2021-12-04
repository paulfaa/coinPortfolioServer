package com.coinportfolio.server;

import com.coinportfolio.server.models.Coin;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@EnableAutoConfiguration
public class AllCoins {

    private ArrayList<Coin> allCoins = new ArrayList<>();
    
    public boolean checkIfListContainsCoin(String coinName){
        if (allCoins != null){
            for (Coin coin: allCoins) {
                if (coin.getName().equalsIgnoreCase(coinName)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkIfListContainsCoin(int coinId){
        if (allCoins != null && coinId >= 0){
            for (Coin coin: allCoins) {
                if (coin.getId() == coinId){
                    return true;
                }
            }
        }
        return false;
    }

    public Coin getCoin(String coinName){
        if (allCoins != null) {
            for (Coin coin : allCoins) {
                if (coin.getName().equalsIgnoreCase(coinName)) {
                    return coin;
                }
            }
        }
        return null;
    }

    public Coin getCoin(int coinId){
        if (allCoins != null && coinId >= 0) {
            for (Coin coin : allCoins) {
                if (coin.getId() == coinId) {
                    return coin;
                }
            }
        }
        return null;
    }

    public int getLength(){
        return allCoins.size();
    }

    public void addCoin(Coin coin){
        allCoins.add(coin);
    }

    public void clearList(){
        allCoins = new ArrayList<>();
    }
}
