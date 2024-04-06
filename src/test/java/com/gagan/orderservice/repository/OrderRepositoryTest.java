package com.gagan.orderservice.repository;

import com.gagan.orderservice.dto.OrderEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource(locations = "classpath:test.properties")
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void shouldGetOrderCount(){
        OrderEntity orderEntity = orderRepository.findOrderById(1L);
        assertEquals(orderEntity.getOrderId(), 1L);
        assertEquals(orderEntity.getOrderName(), "Order 1");
    }

}
