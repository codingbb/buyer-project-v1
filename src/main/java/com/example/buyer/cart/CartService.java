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
    public List<CartResponse.CartDTO> findAll(Integer sessionUserId) {
        List<CartResponse.CartDTO> cartList = cartRepo.findAll();

        //유저별로 장바구니 관리
        List<CartResponse.CartDTO> userCartList = cartList.stream().filter(cart ->
                sessionUserId != null && sessionUserId.equals(cart.getUserId()))
                        .collect(Collectors.toList());

//        System.out.println("카트리스트~ " + cartList);

        return userCartList;
    }


    //장바구니 넣기
    @Transactional
    public Boolean save(Integer userId, Integer productId, Integer buyQty) {
        Cart cart = cartRepo.findByUserAndProductId(userId, productId);

        //장바구니에 중복된 상품이 들어오면 저장 안되어야함
//        if (cart.getUserId().equals(userId) && cart.getProductId().equals(productId)) {
        if (cart != null) {
            return false;

        } else {
            cartRepo.save(userId, productId, buyQty);
            return true;
        }
    }


}
