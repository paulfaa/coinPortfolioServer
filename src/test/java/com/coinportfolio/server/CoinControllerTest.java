package com.coinportfolio.server;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CoinControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testResponseCodes(){
        //valid request
        assertEquals(this.restTemplate.getForEntity("http://localhost:" + port + "/getCoin" + "?id=1", String.class).getStatusCodeValue(), 200);
        //invalid request
        assertEquals(this.restTemplate.getForEntity("http://localhost:" + port + "/getCoin" + "?id=abc", String.class).getStatusCodeValue(), 400);

        Coin c = new Coin(1, "coinName");
        //assertEquals(this.restTemplate.getForObject("http://localhost:" + port + "/getCoin" + "?id=1", String.class), c);
    }
}
