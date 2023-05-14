package com.coinportfolio.server.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CoinIdEnumTest {

    @Test
    public void testGetEnumFromIdSuccess(){
        //Act
        CoinIdEnum result = CoinIdEnum.getEnumFromId(74);

        //Assert
        Assertions.assertEquals(CoinIdEnum.DOGECOIN, result);
    }

    @Test
    public void testGetEnumFromIdInvalidId(){
        //Act
        CoinIdEnum result = CoinIdEnum.getEnumFromId(-1);

        //Assert
        Assertions.assertNull(result);
    }
}
