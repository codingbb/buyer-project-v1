package com.example.buyer.cart;

import lombok.Data;

public class CartRequest {

    @Data
    public static class UpdateDTO {
        private Integer cartId; //cartId
        private Integer buyQty; //수량 변경
    }

}
