package com.example.buyer.cart;

import com.example.buyer.product.Product;
import lombok.Builder;
import lombok.Data;

public class CartResponse {

    // 장바구니용 dto
    @Data
    public static class CartDTO {
        private Integer buyQty;
        private String imgFileName;
        private String pName;    //상품명
        private Integer price;

        @Builder
        public CartDTO(Integer buyQty, String imgFileName, String pName, Integer price) {
            this.buyQty = buyQty;
            this.imgFileName = imgFileName;
            this.pName = pName;
            this.price = price;
        }
    }


}
