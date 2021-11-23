package com.coinportfolio.server;

import com.coinportfolio.server.enums.CurrenciesEnum;
import com.coinportfolio.server.models.Coin;
import com.coinportfolio.server.models.Rate;
import com.coinportfolio.server.utils.CoinNameToCoinmarketId;
import com.coinportfolio.server.service.CoinService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CoinController {

    @Autowired
    CoinService coinService;

    @GetMapping("/getCoin")
    public Coin coin(@RequestParam("id") int id) {
        return new Coin(id, "coinName");
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
    public Rate rate(@RequestParam Map<String, CurrenciesEnum> query) throws JsonProcessingException {
        //stringToEnumConverterFactory should automatically convert string param
        return coinService.processRequestParams(query);
    }

    @GetMapping("/restTemplate")
    public String getFromUrl() throws JsonProcessingException {
        return coinService.getFromUrl();
    }
}
