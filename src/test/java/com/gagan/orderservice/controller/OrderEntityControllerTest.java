package com.gagan.orderservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gagan.orderservice.dto.OrderEntity;
import com.gagan.orderservice.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@TestPropertySource(locations = "classpath:test.properties")
public class OrderEntityControllerTest {

    @MockBean
    private OrderService orderService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllOrders() throws Exception {

        OrderEntity orderEntity1 = OrderEntity.builder().orderName("Order1").orderId(101L).build();
        OrderEntity orderEntity2 = OrderEntity.builder().orderName("Order2").orderId(102L).build();
        List<OrderEntity> orderEntityList = List.of(orderEntity1, orderEntity2);

        when(orderService.getAllOrders()).thenReturn(orderEntityList);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/orders")
                .contentType(APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful())
         .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void saveOrder() throws Exception {
        OrderEntity orderEntity = OrderEntity.builder().orderId(101L).orderName("Order1").build();

        ObjectMapper objectMapper = new ObjectMapper();

        when(orderService.save(orderEntity)).thenReturn(orderEntity);

        mockMvc.perform(post("/order")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderEntity))).andExpect(status().isCreated())
                .andExpect(jsonPath("$.orderId").value(101L));
    }
}
