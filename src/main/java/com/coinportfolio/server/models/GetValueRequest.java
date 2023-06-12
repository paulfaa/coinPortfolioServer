package com.coinportfolio.server.models;

import com.coinportfolio.server.enums.CoinIdEnum;
import com.coinportfolio.server.enums.CurrencyEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetValueRequest {
    private CoinIdEnum id;
    private CurrencyEnum currency;
}