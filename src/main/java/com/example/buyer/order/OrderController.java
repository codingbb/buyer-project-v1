package com.example.buyer.order;

import com.example.buyer.product.Product;
import com.example.buyer.product.ProductResponse;
import com.example.buyer.user.User;
import com.example.buyer.user.UserService;
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

    @PostMapping("/order-cancel")
    public String orderCancel(OrderRequest.CancelDTO requestDTO) {
//        System.out.println("주문 취소 DTO : " + requestDTO);
        orderService.orderCancel(requestDTO);
        return "redirect:/buy-list";
    }


    //장바구니
    @GetMapping("/cart-form")
    public String cartForm() {

        return "/order/cart-form";
    }

    //내가 주문한 상품 확인 폼 //주문한 내역이 나와야함
    @GetMapping("/my-buy-form")
    public String myBuyForm(HttpServletRequest request, @RequestParam Integer orderId) {
        OrderResponse.BuyFormDTO findBuyForm = orderService.findBuyForm(orderId);
        System.out.println("바이폼!! : " + findBuyForm);
        request.setAttribute("buyForm", findBuyForm);
        return "/order/my-buy-form";
    }


    //내 구매목록
    @GetMapping("/buy-list")
    public String buyList(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        List<OrderResponse.ListDTO> orderList = orderService.findListAll(sessionUser.getId());
        System.out.println("오더 리스트 여기! : " + orderList);
        request.setAttribute("orderList", orderList);

        return "/order/buy-list";
    }

    // 주문하기 = 구매하기
    @PostMapping("/order")
    public String order(OrderRequest.DTO requestDTO) {
//        System.out.println(requestDTO);
        orderService.saveOrder(requestDTO);

        return "redirect:/buy-list";

    }

    // 주문폼
    @PostMapping("/order-form")
    public String orderForm(@RequestParam("productId") Integer productId, @RequestParam("buyQty") Integer buyQty, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        //dto 사용해서 한 번에 다 담기
        OrderRequest.ViewDTO dto = orderService.viewForm(sessionUser.getId(), productId, buyQty);
//        System.out.println("주문폼 dto 값 확인 : " + dto);
        request.setAttribute("order", dto);


        //select 2번 해야하지 않을까 ? ?
//        User user = orderService.findByUserId(sessionUser.getId());
//        Product product = orderService.findByProductId(productId);

//        System.out.println("상품 정보 : " + product);
//        System.out.println("수량 받는 qty : " + qty);

        //        Integer price = buyQty * dto.getPrice();

//        TODO: request에 한 번에 담아야하지 않겠니
//        request.setAttribute("user", user);
//        request.setAttribute("product", product);
//        request.setAttribute("price", price);

        return "/order/order-form";
    }


}
