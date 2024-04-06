package com.gagan.orderservice.service;

import com.gagan.orderservice.dto.OrderEntity;
import com.gagan.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderEntity save(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }
}
