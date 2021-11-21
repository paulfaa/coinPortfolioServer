package com.coinportfolio.server;

import com.coinportfolio.server.enums.CurrenciesEnum;
import com.coinportfolio.server.models.Coin;
import com.coinportfolio.server.models.Rate;
import com.coinportfolio.server.utils.CoinNameToCoinmarketId;
import com.coinportfolio.server.utils.CoinmarketApi;
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
    private AllCoins allCoins;

    //move this to coinmarketapi class
    RestTemplate restTemplate = new RestTemplateBuilder(rt-> rt.getInterceptors().add((request, body, execution) -> {
        request.getHeaders().add("X-CMC_PRO_API_KEY", "047f0335-8f37-4cb3-a596-222dac0321a6");
        return execution.execute(request, body);
    })).build();

    @GetMapping("/getCoin")
    public Coin coin(@RequestParam("id") int id) {
        return new Coin(id, "coinName");
    }

    //request params are coin name and the users currency
    @GetMapping("/getRate")
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
    }

    @GetMapping("/getRate")
    public Rate rate(@RequestParam Map<String, CurrenciesEnum> query) throws JsonProcessingException {
        //stringToEnumConverterFactory should automatically convert string param
        CurrenciesEnum currency = query.get(query.keySet().iterator().next());
        boolean coinExists = false;
        if (allCoins.checkIfListContainsCoin("coinName")){
            coinExists = true;
            HashMap<CurrenciesEnum, Rate> rates = allCoins.getCoin("coinName").getCurrencyValues();
            if (rates.containsKey(currency) && LocalDateTime.now().getHour() <= rates.get(currency).getLocalDateTime().getHour() ){
                //if recent rate for this currency already exists, return it
                return rates.get(currency);
            }
        }
        if (coinExists == false){
            allCoins.addCoin(new Coin(CoinNameToCoinmarketId.convertNameToInt("coinName"), "CoinName"));
        }
        Rate rate = new Rate(query.get(query.keySet().iterator().next()), CoinmarketApi.getCoinmarketRateForCoin("coinName"), LocalDateTime.now());
        //update with new rate
        allCoins.getCoin("coinName").setCurrencyValues(currency, rate);
        return rate;
    }

    @GetMapping("/restTemplate")
    public String getFromUrl() throws JsonProcessingException {
        String s = restTemplate.getForObject("https://sandbox-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?id=1",
                String.class);
        return s;
    }
}
