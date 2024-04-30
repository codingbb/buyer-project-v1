package com.example.buyer.orderProduct;

import com.example.buyer.cart.Cart;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderProductService {
    private final OrderProductRepository orderProductRepo;

    @Transactional
    public void saveOrder(OrderProductRequest.SaveDTO requestDTO) {
        orderProductRepo.save(requestDTO);
        orderProductRepo.updateQty(requestDTO);

//        //카트 딜리트
//        if () {
//            orderProductRepo.deleteByCartId();
//        }

    }

}
