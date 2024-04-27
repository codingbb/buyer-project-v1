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

    public OrderRequest.ViewDTO viewForm(Integer sessionUserId, Integer productId, Integer buyQty) {
        User user = orderRepo.findByUserId(sessionUserId);
        Product product = orderRepo.findByProductId(productId);

        Integer sum = buyQty * product.getPrice();

        return new OrderRequest.ViewDTO(user, product, buyQty, sum);
    }


    // 세션에서 유저 조회
    public User findByUserId(Integer id) {
        User user = orderRepo.findByUserId(id);
        return user;
    }

    // 상품 조회
    public Product findByProductId(Integer id) {
        Product product = orderRepo.findByProductId(id);
        return product;
    }


    //구매하기 로직
    @Transactional
    public void saveOrder(OrderRequest.DTO requestDTO) {
        orderRepo.save(requestDTO);
        orderRepo.updateQty(requestDTO);

    }

    //내 구매목록 로직
    public List<OrderResponse.ListDTO> findListAll(Integer sessionUserId) {
        List<OrderResponse.ListDTO> orderList = orderRepo.findAllList();

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
