package com.example.buyer.cart;

import com.example.buyer.product.Product;
import com.example.buyer.product.ProductResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepo;

    //장바구니 삭제하기
    @Transactional
    public void deleteById(Integer id) {
        cartRepo.deleteById(id);

    }


    //장바구니 목록 보기
    public List<CartResponse.CartDTO> findAll() {
        List<CartResponse.CartDTO> cartList = cartRepo.findAll();


        System.out.println(cartList);
        return cartList;
    }


    //장바구니 넣기
    @Transactional
    public Boolean save(Integer userId, Integer productId, Integer buyQty) {
        Cart cart = cartRepo.findByUserAndProductId(userId, productId);

//        if (cart.getUserId().equals(userId) && cart.getProductId().equals(productId)) {
        if (cart != null) {
            return false;

        } else {
            cartRepo.save(userId, productId, buyQty);
            return true;
        }
    }


}
