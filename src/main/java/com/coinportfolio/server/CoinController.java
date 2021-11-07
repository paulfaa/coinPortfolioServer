package com.coinportfolio.server;

import com.coinportfolio.server.enums.CurrenciesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;

@RestController
public class CoinController {

    @Autowired
    private AllCoins allCoins;

    @GetMapping("/getCoin")
    public Coin coin(@RequestParam(value = "id", defaultValue = "1") String name) {
        return new Coin(1, "coinName");
    }

    @GetMapping("/getRate")
    public int coin(@RequestParam(value = "coinName", defaultValue = "noNameSelected!") CurrenciesEnum currency) {
        if (allCoins.checkIfListContainsCoin("coinName")){
            HashMap<CurrenciesEnum, Rate> rates = allCoins.getCoin("coinName").currencyValues;
            if (rates.containsKey(currency) && LocalDateTime.now().getHour() <= rates.get(currency).getLocalDateTime().getHour() ){  //check if rate was already updated in past hour
                return rates.get(currency).getValue();
            }
        }
        //return coinmarketapi.get(coinName);
        return 1;
    }

}
