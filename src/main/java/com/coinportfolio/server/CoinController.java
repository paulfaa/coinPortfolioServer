package com.coinportfolio.server;

import com.coinportfolio.server.enums.CurrenciesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoinController {

    @Autowired
    private AllCoins allCoins;

    //first check server db if coin already exists
    //if so check date, if it's recent enough return these values
    //else make api call and return those values

    private static final String template = "Hello, %s!";
    //private final int counter;

    @GetMapping("/getCoin")
    public Coin coin(@RequestParam(value = "id", defaultValue = "1") String name) {
        return new Coin(1, "coinName");
    }

    @GetMapping("/getRate")
    public Coin coin(@RequestParam(value = "coinName", defaultValue = "noNameSelected!") CurrenciesEnum currency) {
        if (allCoins.checkIfListContainsCoin("coinName")){
            //allCoins.getCoin("coinName").getRate
        }
        return new Coin(1, "coinName");
    }

}
