package com.demo.ecommerce.cucumber;


import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
@PropertySource(value = "classpath:integration.yml")
public class SpringIntegrationTest {

    protected static String URI_MS = "http://localhost:8081";
    protected static String CONTEXT_PATH = "/api/v1/ecommerce/product/price";

    protected static final WebTestClient webTestClient = WebTestClient
            .bindToServer()
            .responseTimeout(Duration.ofMillis(20000))
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .baseUrl(URI_MS)
            .build();

    protected static WebTestClient.ResponseSpec lastResponse;

    protected synchronized void executeGet(String date, String productId, String brandId) {
        lastResponse = webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(CONTEXT_PATH)
                        .queryParam("date", date)
                        .queryParam("productId", productId)
                        .queryParam("brandId", brandId)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
    }
}
