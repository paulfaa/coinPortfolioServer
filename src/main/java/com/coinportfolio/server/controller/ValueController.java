package com.coinportfolio.server.controller;

import com.coinportfolio.server.enums.CoinIdEnum;
import com.coinportfolio.server.enums.CurrencyEnum;
import com.coinportfolio.server.models.Value;
import com.coinportfolio.server.repository.ValueRepository;
import com.coinportfolio.server.service.ValueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ValueController {

    @Autowired
    private ValueServiceImpl valueService;

    @Autowired
    private ValueRepository valueRepository;

    @GetMapping("/values")
    public List<Value> getAll() {
        List<Value> results = new ArrayList<Value>();
        Iterable<Value> savedValues = valueService.getAllValues();
        savedValues.forEach(results::add);
        return results;
    }

    @GetMapping("/values/{id}")
    public ResponseEntity<Value> get(@PathVariable CoinIdEnum id, CurrencyEnum currency) {
        Value value = valueService.getValueByIdAndCurrency(id, currency);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }

    @DeleteMapping("/values")
    public ResponseEntity<Void> deleteAll() {
        valueService.deleteAllValues();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
