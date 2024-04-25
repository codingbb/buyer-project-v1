package com.example.buyer.order;

import com.example.buyer.product.Product;
import com.example.buyer.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class OrderController {
    private final OrderService orderService;
    private final HttpSession session;

// TODO:  - 구매가 되면, 상품의 재고 수정이 필요함 (qty)가 줄어들어야 함.
// TODO:  - 구매가 되면 Order 테이블에 구매목록이 insert되어야 함.
// TODO:  - 구매 목록 보기 기능이 필요함.

    @GetMapping("/cart-form")
    public String cartForm() {

        return "/order/cart-form";
    }

    @GetMapping("/my-buy-form")
    public String myBuyForm() {

        return "/order/my-buy-form";
    }


    // 주문하기
    @PostMapping("/order-form")
    public String orderForm(@RequestParam("productId") Integer productId, @RequestParam("qty") Integer qty, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = orderService.findByUserId(sessionUser.getId());

        Product product = orderService.findByProductId(productId);
        System.out.println("상품 정보 : " + product);
        System.out.println("수량 받는 qty : " + qty);

        request.setAttribute("user", user);
        request.setAttribute("product", product);
        request.setAttribute("orderQty", qty);

        return "/order/order-form";
    }


}
