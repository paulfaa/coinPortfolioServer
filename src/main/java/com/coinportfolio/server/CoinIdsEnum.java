package com.coinportfolio.server;

public enum CoinIdsEnum {
    //enum to map name to  coinmarket holding ids


    BITCOIN(1),
    ETHEREUM(1027);

    private int id;

    CoinIdsEnum(int id) {
        this.id = id;
    }
}
