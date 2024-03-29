package com.coinportfolio.server.controller;

import com.coinportfolio.server.enums.CoinIdEnum;
import com.coinportfolio.server.enums.CurrencyEnum;
import com.coinportfolio.server.exceptions.GetRateException;
import com.coinportfolio.server.models.Coin;
import com.coinportfolio.server.models.Value;
import com.coinportfolio.server.service.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CoinController {

    @Autowired
    CoinService coinService;

    @GetMapping("/getCoin")
    public Coin coin(@RequestParam("id") int id) {
        return new Coin(CoinIdEnum.getEnumFromId(id), "coinName");
    }


    /*@GetMapping("/getRate")
    public long coin(@RequestParam Map<String, CurrenciesEnum> query) {
        //stringToEnumConverterFactory should automatically convert string param

        if (allCoins.checkIfListContainsCoin("coinName")){
            HashMap<CurrenciesEnum, Rate> rates = allCoins.getCoin("coinName").getCurrencyValues();
            CurrenciesEnum currency = query.get(query.keySet().iterator().next());
            if (rates.containsKey(currency) && LocalDateTime.now().getHour() <= rates.get(currency).getLocalDateTime().getHour() ){  //check if rate was already updated in past hour
                return rates.get(currency).getValue();
            }
        }
        //todo: start interacting with api
        //return coinmarketapi.get(coinName);
        restTemplate.getForObject("https://sandbox-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?id=1",
                String.class);
        return 1;
    }*/

    //request params are coin name and the users currency
    @GetMapping("/getRate")
    public Value rate(@RequestParam Map<String, CurrencyEnum> query) throws GetRateException {  //class java.lang.String cannot be cast to class com.coinportfolio.server.enums.CurrenciesEnum
        //stringToEnumConverterFactory should automatically convert string param
        return coinService.processRequestParams(query);
    }

    @GetMapping("/restTemplate")
    public String getFromUrl() {
        return coinService.getFromUrl();
    }

    @GetMapping("/error")
    public String showError(){
        return "Error";
    }
}
