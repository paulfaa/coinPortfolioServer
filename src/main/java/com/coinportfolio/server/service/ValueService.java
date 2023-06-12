package com.coinportfolio.server.service;

import com.coinportfolio.server.enums.CoinIdEnum;
import com.coinportfolio.server.enums.CurrencyEnum;
import com.coinportfolio.server.models.GetValueRequest;
import com.coinportfolio.server.models.Value;
import org.springframework.http.HttpHeaders;

import java.util.Optional;

public interface ValueService {

    Iterable<Value> getAllValues();

    Value getValue(HttpHeaders httpHeaders, GetValueRequest getValueRequest);

    Value saveValue(Value value);

    Value updateValue(CoinIdEnum id, Value value) throws Exception;

    Optional<Value> findByIdAndCurrency(CoinIdEnum id, CurrencyEnum currency);

    void deleteValue(CoinIdEnum id, CurrencyEnum currency);

    void deleteAllValues();
}
