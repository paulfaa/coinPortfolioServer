package com.coinportfolio.server.enums;

public enum CoinIdsEnum {
    //enum to map name to  coinmarket holding ids

    BITCOIN(1),
    ETHEREUM(1027);

    private final int id;

    CoinIdsEnum(int id) {
        this.id = id;
    }

    public int getId(){
        return this.id;
    }
}
