package com.example.buyer.orderProduct;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "order_product_tb")
@Entity
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;    //상품명
    //구매 수량
    private Integer buyQty;
    private Integer orderId;    //주문 pk

    @CreationTimestamp
    private LocalDateTime createdAt;

}
