package com.coinportfolio.server;

import com.coinportfolio.server.enums.CurrenciesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CoinController {

    @Autowired
    private AllCoins allCoins;

    @GetMapping("/getCoin")
    public Coin coin(@RequestParam("id") int id) {
        return new Coin(id, "coinName");
    }

    @GetMapping("/getRate")
    public int coin(@RequestParam Map<String, CurrenciesEnum> query) {
        //stringToEnumConverterFactory should automatically convert string param

        if (allCoins.checkIfListContainsCoin("coinName")){
            HashMap<CurrenciesEnum, Rate> rates = allCoins.getCoin("coinName").currencyValues;
            CurrenciesEnum currency = query.get(query.keySet().iterator().next());
            if (rates.containsKey(currency) && LocalDateTime.now().getHour() <= rates.get(currency).getLocalDateTime().getHour() ){  //check if rate was already updated in past hour
                return rates.get(currency).getValue();
            }
        }
        //todo: start interacting with api
        //return coinmarketapi.get(coinName);
        return 1;
    }

}
