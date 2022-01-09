package com.coinportfolio.server;

import com.coinportfolio.server.enums.CoinIdEnum;
import com.coinportfolio.server.models.Coin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;


public class AllCoinsTest {

    private Coin bitcoin = new Coin(CoinIdEnum.BITCOIN, "Bitcoin");
    private AllCoins allCoins;


    @BeforeEach
    void setup(){
        allCoins = new AllCoins();
    }


    @Test
    public void testCheckIfListContainsCoin(){
        assertEquals(false, allCoins.checkIfListContainsCoin("bitcoin"));
        assertEquals(false, allCoins.checkIfListContainsCoin("bit coin"));
        allCoins.addCoin(bitcoin);
        assertEquals(false, allCoins.checkIfListContainsCoin("bit coin"));
        assertEquals(true, allCoins.checkIfListContainsCoin("Bitcoin"));
        assertEquals(true, allCoins.checkIfListContainsCoin("bitcoin"));
        assertEquals(true, allCoins.checkIfListContainsCoin("BITCOIN"));
    }

    @Test
    public void testCheckIfListContainsCoinInt(){
        assertEquals(false, allCoins.checkIfListContainsCoin(CoinIdEnum.BITCOIN));
        allCoins.addCoin(bitcoin);
        assertEquals(true, allCoins.checkIfListContainsCoin(CoinIdEnum.BITCOIN));
        assertEquals(false, allCoins.checkIfListContainsCoin(CoinIdEnum.DOGECOIN));
    }

    @Test
    public void testGetCoin(){
        assertEquals(null, allCoins.getCoin("bitcoin"));
        allCoins.addCoin(bitcoin);
        Coin foundCoin = allCoins.getCoin("bitcoin");
        assertEquals(bitcoin, foundCoin);
    }
}
