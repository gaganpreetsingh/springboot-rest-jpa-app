package com.gagan.orderservice.service;

import com.gagan.orderservice.dto.OrderEntity;
import com.gagan.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class OrderEntityServiceTest {

    @Autowired
    private OrderRepository orderRepository;

    OrderService orderService;

    @BeforeEach
    public void setUp(){
        orderService = new OrderService(orderRepository);
    }

    @AfterEach
    public void tearDown(){
        orderRepository.deleteAll();
    }

    @Test
    void getAllOrders(){
        OrderEntity orderEntity1 = OrderEntity.builder().orderId(1L).orderName("Order 1").build();

        orderRepository.save(orderEntity1);
        List<OrderEntity> orderEntityList = orderService.getAllOrders();
        assertEquals(orderEntityList.getFirst().getOrderName(), orderEntity1.getOrderName());
        assertEquals(orderEntityList.getFirst().getOrderName(), orderEntity1.getOrderName());
        assertEquals(orderEntityList.getFirst().getOrderId(), orderEntity1.getOrderId());
    }

    @Test void saveOrder(){
        OrderEntity orderEntity1 = OrderEntity.builder().orderId(2L).orderName("Order 2").build();

        OrderEntity order = orderService.save(orderEntity1);
        assertEquals(1, orderRepository.count());
    }

}
