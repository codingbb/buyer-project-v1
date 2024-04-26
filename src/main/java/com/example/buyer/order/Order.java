package com.example.buyer.order;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "order_tb")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;
    private Integer productId;
//    private String state;   //구매 완료, 배송 완료
    private String payment;

    //구매 수량
    private Integer buyQty;

    //합계
    private Integer sum;

    @CreationTimestamp
    private LocalDateTime createdAt;


}
