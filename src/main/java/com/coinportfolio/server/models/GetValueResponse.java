package com.coinportfolio.server.models;

import com.coinportfolio.server.enums.CoinIdEnum;
import com.coinportfolio.server.enums.CurrencyEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class GetValueResponse {
    private CoinIdEnum id;
    private CurrencyEnum currency;
    private BigDecimal value;
    private LocalDateTime timestamp;

}
