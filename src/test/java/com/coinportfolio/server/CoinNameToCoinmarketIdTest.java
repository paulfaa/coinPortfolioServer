package com.coinportfolio.server;

import com.coinportfolio.server.utils.CoinNameToCoinmarketId;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
public class CoinNameToCoinmarketIdTest {

    @Test
    void testConvertValidName() {
        assertEquals(1, CoinNameToCoinmarketId.convertNameToInt("BITCOIN"));
    }

    @Test
    void testConvertInvalidName() {
        //return 0 for all invalid names
        assertEquals(0, CoinNameToCoinmarketId.convertNameToInt("DoesNotExist"));
    }

    @Test
    void testConvertValidIdToName() {
        //coinmarketcap ID for bitcoin is 1
        assertEquals("BITCOIN", CoinNameToCoinmarketId.convertIdToName(1));
    }

    @Test
    void testConvertInvalidIdToName() {
        //method returns null for all invalid ids
        assertNull(CoinNameToCoinmarketId.convertIdToName(-1));
    }
}
