package com.coinportfolio.server;

import com.coinportfolio.server.utils.CoinNameToCoinmarketId;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
public class CoinNameToCoinmarketIdTest {

    @Test
    void testConvertName() {
        assertEquals(1, CoinNameToCoinmarketId.convertNameToInt("BITCOIN"));
        //return 0 for all invalid names
        assertEquals(0, CoinNameToCoinmarketId.convertNameToInt("DoesNotExist"));
    }

    @Test
    void testConvertIdToName() {
        //coinmarketcap ID for bitcoin is 1
        assertEquals("BITCOIN", CoinNameToCoinmarketId.convertIdToName(1));
        //method returns null for all invalid ids
        assertNull(CoinNameToCoinmarketId.convertIdToName(-1));
    }
}
