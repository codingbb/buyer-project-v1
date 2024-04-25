package com.example.buyer.order;

import com.example.buyer.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class OrderController {
    private final OrderService orderService;
    private final HttpSession session;

    @GetMapping("/cart-form")
    public String cartForm() {

        return "/order/cart-form";
    }

    @GetMapping("/my-buy-form")
    public String myBuyForm() {

        return "/order/my-buy-form";
    }

    @GetMapping("/order-form")
    public String orderForm() {
        User sessionUser = (User) session.getAttribute("sessionUser");


        return "/order/order-form";
    }


}
