package com.example.buyer.order;

import com.example.buyer.product.Product;
import com.example.buyer.product.ProductResponse;
import com.example.buyer.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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


    //내 구매목록
    @GetMapping("/buy-list")
    public String buyList(HttpServletRequest request) {
        List<OrderResponse.ListDTO> orderList = orderService.findAll();
        System.out.println("오더 리스트 여기! : " + orderList);
        request.setAttribute("orderList", orderList);

        return "/order/buy-list";
    }

    // 주문하기
    @PostMapping("/order")
    public String order(OrderRequest.DTO requestDTO) {
//        System.out.println(requestDTO);
        orderService.saveOrder(requestDTO);

        return "redirect:/buy-list";

    }


    // 주문폼
    @PostMapping("/order-form")
    public String orderForm(@RequestParam("productId") Integer productId, @RequestParam("qty") Integer qty, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = orderService.findByUserId(sessionUser.getId());

        Product product = orderService.findByProductId(productId);
//        System.out.println("상품 정보 : " + product);
//        System.out.println("수량 받는 qty : " + qty);

        Integer price = qty * product.getPrice();

//        TODO: request에 한 번에 담아야하지 않겠니
        request.setAttribute("user", user);
        request.setAttribute("product", product);
        request.setAttribute("orderQty", qty);
        request.setAttribute("price", price);

        return "/order/order-form";
    }


}
