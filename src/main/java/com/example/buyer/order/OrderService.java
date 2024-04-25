package com.example.buyer.order;

import com.example.buyer.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepo;

    public User findByUserId(Integer id) {
        User user = orderRepo.findByUserId(id);
        return user;
    }
}
