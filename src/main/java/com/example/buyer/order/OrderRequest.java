package com.example.buyer.order;

import lombok.Data;

public class OrderRequest {

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
        private Integer sum; //합계


    }

}
