package com.example.buyer.cart;

import com.example.buyer.product.Product;
import com.example.buyer.product.ProductResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepo;

    //장바구니 목록 보기
    public List<CartResponse.CartDTO> findAll() {
        List<CartResponse.CartDTO> cartList = cartRepo.findAll();
        System.out.println("서비스 :" + cartList);
        return cartList;
    }


    //장바구니 넣기
    @Transactional
    public void save(Integer userId, Integer productId, Integer buyQty) {
        cartRepo.save(userId, productId, buyQty);

    }

}
