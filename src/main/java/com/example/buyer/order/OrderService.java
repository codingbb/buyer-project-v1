package com.example.buyer.order;

import com.example.buyer.product.Product;
import com.example.buyer.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepo;

    // 세션에서 유저 조회
    public User findByUserId(Integer id) {
        User user = orderRepo.findByUserId(id);
        return user;
    }

    // 상품 조회
    public Product findByProductId(Integer id) {
        Product product = orderRepo.findByProductId(id);
        return product;
    }


}
