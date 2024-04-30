package com.example.buyer.cart;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "cart_tb")
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;
    private Integer productId;

    //구매 수량
    private Integer buyQty;

    //장바구니에 체크한 것만 가져오게
    private Boolean status;     //true 체크 함, false - 체크안함

    @CreationTimestamp
    private LocalDateTime createdAt;


}
