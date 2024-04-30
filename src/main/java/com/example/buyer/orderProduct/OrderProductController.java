package com.example.buyer.orderProduct;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class OrderProductController {
    private final OrderProductService orderProductService;

}
