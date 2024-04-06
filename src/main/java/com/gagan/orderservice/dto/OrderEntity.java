package com.gagan.orderservice.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "order_entity")
public class OrderEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "order_name")
    private String orderName;
}
