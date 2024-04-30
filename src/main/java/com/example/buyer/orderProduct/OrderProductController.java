package com.example.buyer.orderProduct;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class OrderProductController {
    private final OrderProductService orderProductService;

    @PostMapping("/order-product-save")
    public String save(OrderProductRequest.SaveDTO requestDTO) {
//        System.out.println("구매하기 : " + requestDTO);
        orderProductService.saveOrder(requestDTO);

        return "redirect:/order-list";

    }

}
