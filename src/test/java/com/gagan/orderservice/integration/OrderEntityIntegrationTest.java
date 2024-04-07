package com.gagan.orderservice.integration;

import com.gagan.orderservice.dto.OrderEntity;
import io.micrometer.core.instrument.util.IOUtils;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class OrderEntityIntegrationTest {

    @LocalServerPort
    private int port;

    TestRestTemplate testRestTemplate = new TestRestTemplate();

    @BeforeEach
    public void setup(){

    }

    @Test
    public void testGetOrdersCall() throws IOException, JSONException {
        ResponseEntity<String> responseEntity =  testRestTemplate.exchange(String.format("http://localhost:%s/orders", port),
                HttpMethod.GET, null, String.class);

        System.out.println(responseEntity.getBody());

        String expectedJson = IOUtils.toString(new ClassPathResource("orders_response.json").getInputStream(), StandardCharsets.UTF_8);
        JSONAssert.assertEquals(responseEntity.getBody(), expectedJson, false);
    }

    @Test
    public void testPostorderCall() {
        OrderEntity orderEntity = OrderEntity.builder().orderId(5L).orderName("Order 5").build();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", APPLICATION_JSON_VALUE);
        HttpEntity<OrderEntity> httpEntity = new HttpEntity<>(orderEntity, httpHeaders);
        ResponseEntity<OrderEntity> response = testRestTemplate.exchange(
                String.format("http://localhost:%s/order", port),
                HttpMethod.POST,
                httpEntity,
                OrderEntity.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(orderEntity, response.getBody());
    }
}
