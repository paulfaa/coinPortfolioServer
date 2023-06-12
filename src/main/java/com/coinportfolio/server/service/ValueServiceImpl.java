package com.coinportfolio.server.service;

import com.coinportfolio.server.enums.CoinIdEnum;
import com.coinportfolio.server.enums.CurrencyEnum;
import com.coinportfolio.server.models.GetValueRequest;
import com.coinportfolio.server.models.Value;
import com.coinportfolio.server.repository.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;


@Service
public class ValueServiceImpl implements ValueService {
    @Autowired
    private ValueRepository valueRepository;

    @Override
    public Iterable<Value> getAllValues() {
        return valueRepository.findAll();
    }

    @Override
    public Value getValue(HttpHeaders httpHeaders, GetValueRequest getValueRequest) {
        LocalDateTime now = LocalDateTime.now();
        Optional<Value> result = valueRepository.findByIdAndCurrency(getValueRequest.getId(), getValueRequest.getCurrency());
        if(result.isEmpty()){
            //try call api and populate db with new row, and return result
            Value dummyValue = new Value(getValueRequest.getCurrency(), new BigDecimal(123), LocalDateTime.now());
            valueRepository.save(dummyValue);
            return dummyValue;
        }
        else{
            LocalDateTime storedUpdateDate = result.get().getUpdateDate();
            if(storedUpdateDate.isBefore(now.plus(1, ChronoUnit.HOURS)) && storedUpdateDate.isAfter(now.minus(1, ChronoUnit.HOURS))){
                return result.get();
            }
            else {
                //rate is too old - try update stored rate in db, and return result
                Value dummyValue = new Value(getValueRequest.getCurrency(), new BigDecimal(456), LocalDateTime.now());
                valueRepository.save(dummyValue);
                return dummyValue;
            }
        }
    }

    @Override
    public Value saveValue(Value value) {
        return null;
    }

    @Override
    public Value updateValue(CoinIdEnum id, Value value) throws Exception {
        return null;
    }

    @Override
    public Optional<Value> findByIdAndCurrency(CoinIdEnum id, CurrencyEnum currency) {
        return Optional.empty();
    }

    @Override
    public void deleteValue(CoinIdEnum id, CurrencyEnum currency) {

    }

    @Override
    public void deleteAllValues() {
        valueRepository.deleteAll();
    }
}
