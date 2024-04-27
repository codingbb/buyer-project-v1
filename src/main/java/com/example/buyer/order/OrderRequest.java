package com.example.buyer.order;

import com.example.buyer.product.Product;
import com.example.buyer.user.User;
import lombok.Builder;
import lombok.Data;

public class OrderRequest {

    //주문 취소용 정보 받는 dto
    @Data
    public static class CancelDTO {
        private Integer orderId;
        private Integer buyQty;
        private String status;
    }


    //주문 폼 DTO
    @Data
    public static class ViewDTO {
        // 유저 정보
        private Integer userId;
        private String name;    //유저 성명
        private String address;
        private String phone;

        //주문 상품 정보 //product
        private Integer productId;
        private String productName;     //상품 이름
        private Integer price;

        //주문한 상품 수량
        private Integer buyQty;
        private Integer sum;

        @Builder
        public ViewDTO(User user, Product product, Integer buyQty, Integer sum) {
            this.userId = user.getId();
            this.name = user.getName();
            this.address = user.getAddress();
            this.phone = user.getPhone();
            this.productId = product.getId();
            this.productName = product.getName();
            this.price = product.getPrice();
            this.buyQty = buyQty;
            this.sum = sum;
        }
    }

    @Data
    public static class DTO {
        // user 들고 오는 부분
        private Integer userId;
        private String name;
        private String address;
        private String phone;
        private String payment;

        //product 들고 오는 부분
        private Integer productId;
        private String pName;
        private Integer buyQty;    //선택한 수량
        private Integer price;  //계산된 가격

        //order에 넣는 부분
        private Integer sum; //합계
        private String status;

    }

}
