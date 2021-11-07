package com.coinportfolio.server;

import com.coinportfolio.server.utils.CoinNameToCoinmarketId;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CoinNameToCoinmarketIdTest {

    private CoinNameToCoinmarketId coinNameToCoinmarketId = new CoinNameToCoinmarketId();

    @Test
    void testConvertName() {
        assertEquals(1, coinNameToCoinmarketId.convertNameToInt("BITCOIN"));
        //return 0 for all invalid names
        assertEquals(0, coinNameToCoinmarketId.convertNameToInt("DoesNotExist"));
    }

    @Test
    void testConvertIdToName() {
        assertEquals("BITCOIN", coinNameToCoinmarketId.convertIdToName(1));
        assertNull(coinNameToCoinmarketId.convertIdToName(-1));
    }
}
