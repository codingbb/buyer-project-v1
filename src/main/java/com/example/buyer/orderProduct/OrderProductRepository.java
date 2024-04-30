package com.example.buyer.orderProduct;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderProductRepository {
    private final EntityManager em;

}
