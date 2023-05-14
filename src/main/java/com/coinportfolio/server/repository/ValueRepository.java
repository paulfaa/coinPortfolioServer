package com.coinportfolio.server.repository;

import com.coinportfolio.server.enums.CoinIdEnum;
import com.coinportfolio.server.enums.CurrencyEnum;
import com.coinportfolio.server.models.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ValueRepository extends JpaRepository<Value, CoinIdEnum> {

    Optional<Value> findByIdAndCurrency(CoinIdEnum id, CurrencyEnum currency);

}