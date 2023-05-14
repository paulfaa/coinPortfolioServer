package com.coinportfolio.server.models;

import com.coinportfolio.server.enums.CurrenciesEnum;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValueTest {

    public Value value;

    @BeforeEach
    public void setup(){
        value = new Value(CurrenciesEnum.EUR, BigDecimal.valueOf(0.55), LocalDateTime.now());
    }

    @Test
    public void testEqualsSuccess() { //values are considered equal if they are updated within 1hr of each other
        //Arrange
        Value otherValue1 = new Value(CurrenciesEnum.EUR, BigDecimal.valueOf(0.55), LocalDateTime.now().minus(50, ChronoUnit.MINUTES));
        Value otherValue2= new Value(CurrenciesEnum.EUR, BigDecimal.valueOf(0.55), LocalDateTime.now());

        //Assert
        Assertions.assertEquals(value, otherValue1);
        Assertions.assertEquals(value, otherValue2);
    }

    @Test
    public void testEqualsFailureDifferentTime() { //ensure values are considered equal if they are updated within 1hr of each other
        //Arrange
        Value otherValue1 = new Value(CurrenciesEnum.EUR, BigDecimal.valueOf(4.56), LocalDateTime.now().minus(2, ChronoUnit.HOURS));
        Value otherValue2= new Value(CurrenciesEnum.EUR, BigDecimal.valueOf(4.56), LocalDateTime.now().plus(3, ChronoUnit.YEARS));

        //Assert
        Assertions.assertNotEquals(value, otherValue1);
        Assertions.assertNotEquals(value, otherValue2);
    }

    @Test
    public void testEqualsFailureDifferentCurrency() {
        //Arrange
        Value otherValue1 = new Value(CurrenciesEnum.USD, BigDecimal.valueOf(4.56), LocalDateTime.now());

        //Assert
        Assertions.assertNotEquals(value, otherValue1);
    }
}
