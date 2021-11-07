package com.coinportfolio.server.utils;

import com.coinportfolio.server.enums.CoinIdsEnum;

public class CoinNameToCoinmarketId {

    public int convertNameToInt(String coinName){
        try{
            return CoinIdsEnum.valueOf(coinName).getId();
        }
        catch (Exception e){
            return 0;
        }
    }

    public String convertIdToName(int id){
        for (CoinIdsEnum e : CoinIdsEnum.values()) {
            if (e.getId() == (id)) {
                return e.toString();
            }
        }
        return null;
    }
}
