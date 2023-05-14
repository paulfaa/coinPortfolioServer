package com.coinportfolio.server.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CoinNameToCoinmarketIdTest {

    @Test
    void testConvertValidName() {
        Assertions.assertEquals(1, CoinNameToCoinmarketId.convertNameToInt("BITCOIN"));
    }

    @Test
    void testConvertInvalidName() {
        //return 0 for all invalid names
        Assertions.assertEquals(0, CoinNameToCoinmarketId.convertNameToInt("DoesNotExist"));
    }

    @Test
    void testConvertValidIdToName() {
        //coinmarketcap ID for bitcoin is 1
        Assertions.assertEquals("BITCOIN", CoinNameToCoinmarketId.convertIdToName(1));
    }

    @Test
    void testConvertInvalidIdToName() {
        //method returns null for all invalid ids
        Assertions.assertNull(CoinNameToCoinmarketId.convertIdToName(-1));
    }
}
