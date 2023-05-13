package com.coinportfolio.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WebMvcTest(CoinController.class)
public class CoinControllerTest {

    @LocalServerPort
    private int port;

    private RestTemplate restTemplate;

    @Autowired
    private MockMvc mvc;

    private String localhost = "http://localhost:" + port;

    /*@Before
    public void setUp(){
        restTemplate = new RestTemplateBuilder(rt-> rt.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("X-CMC_PRO_API_KEY", "047f0335-8f37-4cb3-a596-222dac0321a6");
            return execution.execute(request, body);
        })).build();
    }

    @Test
    public void testResponseCodes(){
        //valid request
        assertEquals(this.restTemplate.getForEntity(localhost + "/getCoin?id=1", String.class).getStatusCodeValue(), 200);

        //invalid request
        assertEquals(this.restTemplate.getForEntity(localhost + "/getCoin?id=abc", String.class).getStatusCodeValue(), 400);

        Coin c = new Coin(1, "coinName");
        //assertEquals(this.restTemplate.getForObject("http://localhost:" + port + "/getCoin" + "?id=1", String.class), c);
        assertEquals(c, this.restTemplate.getForObject("https://sandbox-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?id=1",
                String.class));

    }

    @Test
    public void testGetRateMapping() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/getRate")
                .param("Coin name", "Bitcoin")
                .param("Currency", "EUR"))
                .andExpect(content().contentType("application/json"));
    }*/
}
