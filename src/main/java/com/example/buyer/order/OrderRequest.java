package com.example.buyer.order;

import lombok.Data;

public class OrderRequest {

    @Data
    public static class DTO {
        // user 들고 오는 부분
        private String name;
        private String address;
        private String phone;
        private String payment;

    }

}
