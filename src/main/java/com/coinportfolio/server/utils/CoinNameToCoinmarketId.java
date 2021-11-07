package com.coinportfolio.server.utils;

import com.coinportfolio.server.enums.CoinIdsEnum;

public class CoinNameToCoinmarketId {

    public int convertName(String coinName){
        return CoinIdsEnum.valueOf(coinName).ordinal();
    }

    public String convertId(int id){
        for (CoinIdsEnum e : CoinIdsEnum.values()) {
            if (e.getId() == (id)) {
                return e.toString();
            }
        }
        return null;
    }
}
