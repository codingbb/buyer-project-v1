package com.example.buyer.order;

import com.example.buyer.product.Product;
import com.example.buyer.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepo;

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


    @Transactional
    public void saveOrder(OrderRequest.DTO requestDTO) {
        orderRepo.save(requestDTO);
        orderRepo.updateQty(requestDTO);

    }

    public List<OrderResponse.ListDTO> findAll() {
        List<OrderResponse.ListDTO> orderList = orderRepo.findAll();

        Integer indexNum = orderList.size();
        for (OrderResponse.ListDTO listNum : orderList) {
            listNum.setIndexNum(indexNum--);
        }

        return orderList;
    }
}
