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

    public List<Cart> cartAll(Integer sessionUserId) {
        //장바구니 품목 전체 조회
        List<Cart> cartList = cartRepo.findAllCart(sessionUserId);
        System.out.println("여기 Boolean값 있나 : " + cartList);

        return cartList;
    }


    //장바구니에서 체크된 값만 넣어야함
    @Transactional
    public void checkCartUpdate(List<CartRequest.UpdateDTO> updateDTOs, Integer sessionUserId) {

        List<Cart> cartList = cartRepo.findAllCart(sessionUserId);

        //status = true로 들어옴!! 안된 애들 삭제하려고

        //장바구니에서 체크한 애들은 수량 업데이트
        cartRepo.updateCheckProduct(updateDTOs, sessionUserId);
        //TODO: 장바구니에서 체크 안 된 애들은 삭제

    }


    //장바구니 수량 변경
    @Transactional
    public void updateQty(CartRequest.UpdateDTO requestDTO) {
        cartRepo.updateQty(requestDTO);
    }


    //장바구니 삭제하기
    @Transactional
    public void cartDelete(Integer id) {
        cartRepo.deleteById(id);

    }


    //장바구니 목록 보기
    public List<CartResponse.CartDTO> getCartList(Integer sessionUserId) {
        List<CartResponse.CartDTO> cartList = cartRepo.findAll();

        //유저별로 장바구니 관리
        List<CartResponse.CartDTO> cartWithUser = cartList.stream().filter(cart ->
                sessionUserId != null && sessionUserId.equals(cart.getUserId()))
                        .collect(Collectors.toList());

//        System.out.println("카트리스트~ " + cartList);

        return cartWithUser;
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
