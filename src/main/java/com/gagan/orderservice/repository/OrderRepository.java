package com.gagan.orderservice.repository;

import com.gagan.orderservice.dto.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query(value = "SELECT * from Order_Entity where order_Id=:orderId", nativeQuery = true)
    OrderEntity findOrderById(@Param("orderId") Long orderId);
}
