package com.coinportfolio.server.enums;

public enum CoinIdEnum {
    //enum to map name to  coinmarket holding ids

    BITCOIN(1),
    LITECOIN(2),
    XRP(52),
    DOGECOIN(74),
    DASH(131),
    STELLAR(512),
    ETHEREUM(1027);

    private final int id;

    CoinIdEnum(int id) {
        this.id = id;
    }

    public int getId(){
        return this.id;
    }
}
