package com.coinportfolio.server.utils;

import com.coinportfolio.server.enums.CoinIdEnum;

public final class CoinNameToCoinmarketId {

    //return 0 for invalid
    public static int convertNameToInt(String coinName){
        try{
            return CoinIdEnum.valueOf(coinName.toUpperCase()).getId();
        }
        catch (Exception e){
            return 0;
        }
    }

    //return null for invalid
    public static String convertIdToName(int id){
        try {
            for (CoinIdEnum e : CoinIdEnum.values()) {
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
