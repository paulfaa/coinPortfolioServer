package com.coinportfolio.server.utils;

import com.coinportfolio.server.enums.CoinIdsEnum;

public final class CoinNameToCoinmarketId {

    //return 0 for invalid
    public static int convertNameToInt(String coinName){
        try{
            return CoinIdsEnum.valueOf(coinName.toUpperCase()).getId();
        }
        catch (Exception e){
            return 0;
        }
    }

    //return null for invalid
    public static String convertIdToName(int id){
        try {
            for (CoinIdsEnum e : CoinIdsEnum.values()) {
                if (e.getId() == (id)) {
                    return e.toString();
                }
            }
        }
        catch (Exception e){
            return null;
        }
        return null;
    }
}
