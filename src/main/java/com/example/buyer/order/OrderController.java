package com.example.buyer.order;

import com.example.buyer.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class OrderController {
    private final OrderService orderService;
    private final HttpSession session;

    //주문 취소 로직
    @PostMapping("/order-cancel")
    public String orderCancel(OrderRequest.CancelDTO requestDTO) {
//        System.out.println("주문 취소 DTO : " + requestDTO);
        orderService.orderCancel(requestDTO);
        return "redirect:/order-list";
    }

    //내가 주문한 상품 상세보기 폼 //주문한 내역이 나와야함
    @GetMapping("/order-detail")
    public String detail(HttpServletRequest request, @RequestParam Integer orderId) {
        OrderResponse.DetailDTO orderDetail = orderService.orderDetail(orderId);
//        System.out.println("주문상세보기 DTO : " + orderDetail);
        request.setAttribute("orderDetail", orderDetail);
        return "/order/order-detail-form";
    }


    //내 구매목록 리스트
    @GetMapping("/order-list")
    public String orderList(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        List<OrderResponse.ListDTO> orderList = orderService.orderList(sessionUser.getId());
//        System.out.println("오더 리스트 : " + orderList);
        request.setAttribute("orderList", orderList);

        return "/order/order-list";
    }

    // 주문하기 = 구매하기
    @PostMapping("/order-save")
    public String save(OrderRequest.SaveDTO requestDTO) {
//        System.out.println("구매하기 : " + requestDTO);
        orderService.saveOrder(requestDTO);

        return "redirect:/order-list";

    }

    // 주문하려는 물품 구매 폼
    @GetMapping("/order-save-form")
    public String orderCheckForm() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        //지금 화면에 뿌려질게 화면에 뿌려질 유저 정보와 장바구니에 담은 상품 정보를 받아와서 뿌려야함!!!@
        List<OrderResponse.SaveFormDTO> orderList = orderService.내장바구니내역(sessionUser.getId());
        System.out.println("허어어어 : " + orderList);
//        OrderResponse.SaveFormDTO order = orderService.orderCheck(sessionUser.getId(), productId, buyQty);
//        request.setAttribute("order", order);

        // 모델에(request) 담기


        return "/order/order-save-form";
    }

    // 주문폼 //Get 요청이겠지?? POST? GET? POST? GET?
//    @PostMapping("/order-form")
//    public String orderFormPost() {
//
//        return "/order/order-form";
//    }


}
