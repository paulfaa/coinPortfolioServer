package com.coinportfolio.server;

import com.coinportfolio.server.enums.CoinIdEnum;
import com.coinportfolio.server.models.Coin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AllCoinsTest {

    private final Coin bitcoin = new Coin(CoinIdEnum.BITCOIN, "Bitcoin");
    private AllCoins allCoins;


    @BeforeEach
    void setup(){
        allCoins = new AllCoins();
    }


    @Test
    public void testCheckIfListContainsCoin(){
        Assertions.assertFalse(allCoins.checkIfListContainsCoin("bitcoin"));
        Assertions.assertFalse(allCoins.checkIfListContainsCoin("bit coin"));
        allCoins.addCoin(bitcoin);
        Assertions.assertFalse(allCoins.checkIfListContainsCoin("bit coin"));
        Assertions.assertTrue(allCoins.checkIfListContainsCoin("Bitcoin"));
        Assertions.assertTrue(allCoins.checkIfListContainsCoin("bitcoin"));
        Assertions.assertTrue(allCoins.checkIfListContainsCoin("BITCOIN"));
    }

    @Test
    public void testCheckIfListContainsCoinInt(){
        Assertions.assertFalse(allCoins.checkIfListContainsCoin(CoinIdEnum.BITCOIN));
        allCoins.addCoin(bitcoin);
        Assertions.assertTrue(allCoins.checkIfListContainsCoin(CoinIdEnum.BITCOIN));
        Assertions.assertFalse(allCoins.checkIfListContainsCoin(CoinIdEnum.DOGECOIN));
    }

    @Test
    public void testGetCoin(){
        Assertions.assertNull(allCoins.getCoin("bitcoin"));
        allCoins.addCoin(bitcoin);
        Coin foundCoin = allCoins.getCoin("bitcoin");
        Assertions.assertEquals(bitcoin, foundCoin);
    }
}
