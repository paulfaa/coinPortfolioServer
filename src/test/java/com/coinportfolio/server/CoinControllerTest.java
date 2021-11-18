package com.coinportfolio.server;

import com.coinportfolio.server.models.Coin;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CoinControllerTest {

    @LocalServerPort
    private int port;

    RestTemplate restTemplate = new RestTemplateBuilder(rt-> rt.getInterceptors().add((request, body, execution) -> {
        request.getHeaders().add("X-CMC_PRO_API_KEY", "047f0335-8f37-4cb3-a596-222dac0321a6");
        return execution.execute(request, body);
    })).build();

    @Test
    public void testResponseCodes(){
        //valid request
        //assertEquals(this.restTemplate.getForEntity("http://localhost:" + port + "/getCoin" + "?id=1", String.class).getStatusCodeValue(), 200);

        //invalid request
        //assertEquals(this.restTemplate.getForEntity("http://localhost:" + port + "/getCoin" + "?id=abc", String.class).getStatusCodeValue(), 400);

        Coin c = new Coin(1, "coinName");
        //assertEquals(this.restTemplate.getForObject("http://localhost:" + port + "/getCoin" + "?id=1", String.class), c);
        assertEquals(c, this.restTemplate.getForObject("https://sandbox-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?id=1",
                String.class));
        //need to write class to parse json

    }
}
