package com.coinportfolio.server;

import com.coinportfolio.server.enums.CoinIdEnum;
import com.coinportfolio.server.models.Coin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AllCoinsTest {

    private Coin bitcoin = new Coin(CoinIdEnum.BITCOIN, "Bitcoin");
    private AllCoins allCoins;


    @BeforeEach
    void setup(){
        allCoins = new AllCoins();
    }


    @Test
    public void testCheckIfListContainsCoin(){
        Assertions.assertEquals(false, allCoins.checkIfListContainsCoin("bitcoin"));
        Assertions.assertEquals(false, allCoins.checkIfListContainsCoin("bit coin"));
        allCoins.addCoin(bitcoin);
        Assertions.assertEquals(false, allCoins.checkIfListContainsCoin("bit coin"));
        Assertions.assertEquals(true, allCoins.checkIfListContainsCoin("Bitcoin"));
        Assertions.assertEquals(true, allCoins.checkIfListContainsCoin("bitcoin"));
        Assertions.assertEquals(true, allCoins.checkIfListContainsCoin("BITCOIN"));
    }

    @Test
    public void testCheckIfListContainsCoinInt(){
        Assertions.assertEquals(false, allCoins.checkIfListContainsCoin(CoinIdEnum.BITCOIN));
        allCoins.addCoin(bitcoin);
        Assertions.assertEquals(true, allCoins.checkIfListContainsCoin(CoinIdEnum.BITCOIN));
        Assertions.assertEquals(false, allCoins.checkIfListContainsCoin(CoinIdEnum.DOGECOIN));
    }

    @Test
    public void testGetCoin(){
        Assertions.assertEquals(null, allCoins.getCoin("bitcoin"));
        allCoins.addCoin(bitcoin);
        Coin foundCoin = allCoins.getCoin("bitcoin");
        Assertions.assertEquals(bitcoin, foundCoin);
    }
}
