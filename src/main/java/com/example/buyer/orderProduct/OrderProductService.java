package com.example.buyer.orderProduct;

import com.example.buyer.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderProductService {
    private final OrderRepository orderRepo;


}
