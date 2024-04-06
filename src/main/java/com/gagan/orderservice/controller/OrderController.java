package com.gagan.orderservice.controller;

import com.gagan.orderservice.dto.OrderEntity;
import com.gagan.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders")
    List<OrderEntity> getOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/order")
    ResponseEntity<OrderEntity> saveOrder(@RequestBody OrderEntity orderEntity){
        return new ResponseEntity<>(orderService.save(orderEntity), CREATED);
    }
}
