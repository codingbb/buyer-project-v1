package com.example.buyer.order;

import com.example.buyer.product.Product;
import com.example.buyer.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepo;

    //유저 조회용...
    public User 유저조회(Integer sessionUserId) {
        User user = orderRepo.findByUserId(sessionUserId);
        return user;
    }

    //내 장바구니 내역에 있는 상품들을 구매폼에 불러오기
    public List<OrderResponse.SaveFormDTO> 내장바구니내역(Integer sessionUserId) {
        List<OrderResponse.SaveFormDTO> cartList = orderRepo.내장바구니내역(sessionUserId);
//        System.out.println("내 장바구니 내역 : " + cartList);

        Integer sum;    //물건 각각 합계
//        Integer totalSum = 0;   //토탈 총액
        for (OrderResponse.SaveFormDTO cart : cartList) {
            sum = cart.getPrice() * cart.getBuyQty();
            cart.setSum(sum);

//            totalSum = totalSum + sum;
//            cart.setTotalSum(totalSum);
        }

        return cartList;

    }

    //주문폼
//    OrderResponse.SaveFormDTO 디테일주문폼(Integer sessionUserId) {
//        Product product = orderRepo.findByProductId(productId);
//
//        Integer sum = buyQty * product.getPrice();
//
//        return new OrderRequest.ViewDTO(user, product, buyQty, sum);
//    }


    //주문 취소하기!!
    @Transactional
    public void orderCancel(OrderRequest.CancelDTO requestDTO) {
        orderRepo.findByIdAndUpdateStatus(requestDTO);
    }


    //내 주문 내역 폼 order-detail-form
    public OrderResponse.DetailDTO orderDetail(Integer orderId) {
        OrderResponse.DetailDTO orderDetail = orderRepo.findUserProductByOrderId(orderId);

//        System.out.println("dto 값 확인용!! " + orderDetail);

        return orderDetail;
    }


    //주문폼 orderViewForm //responseDTO인가 ? ? ?
//    public OrderResponse.SaveFormDTO orderCheck(Integer sessionUserId, Integer productId, Integer buyQty) {
//        User user = orderRepo.findByUserId(sessionUserId);
//        Product product = orderRepo.findByProductId(productId);
//
//        Integer sum = buyQty * product.getPrice();
//
//        return new OrderResponse.SaveFormDTO(user, product, buyQty, sum);
//    }


    //구매하기 로직
//    @Transactional
//    public void saveOrder(OrderRequest.SaveDTO requestDTO) {
//        orderRepo.save(requestDTO);
//        orderRepo.updateQty(requestDTO);
//
//    }

    //내 구매목록 로직
    public List<OrderResponse.ListDTO> orderList(Integer sessionUserId) {
        List<OrderResponse.ListDTO> orderList = orderRepo.findAllOrder();

        //ssar 유저가 구매한 내역만 나와야함
        //필터를 쓰는구나..............!!!!!!!!!!!!!!
        List<OrderResponse.ListDTO> findUserOrderList = orderList.stream().filter(list ->
                sessionUserId != null && sessionUserId.equals(list.getUserId()))
                .collect(Collectors.toList());

        // 화면의 No용
        Integer indexNum = findUserOrderList.size();
        for (OrderResponse.ListDTO listNum : findUserOrderList) {
            listNum.setIndexNum(indexNum--);
        }

        return findUserOrderList;
    }


}
