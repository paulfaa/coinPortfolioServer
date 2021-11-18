package com.coinportfolio.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ServerApplication {

	private String testApiKey = "047f0335-8f37-4cb3-a596-222dac0321a6";

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public WebClient webClient() {
		WebClient client = WebClient.builder()
				.baseUrl("sandbox-api.coinmarketcap.com")
				.defaultCookie("cookieKey", "cookieValue")
				.defaultHeaders(httpHeaders -> {
					//httpHeaders.set("X-CMC_PRO_API_KEY", testApiKey);
					httpHeaders.add("X-CMC_PRO_API_KEY", testApiKey);
				}).build();
		return client;
	}
}
