package com.example.buyer.order;

import lombok.Data;

public class OrderResponse {

    @Data
    public static class ListDTO {
        private String pName;
        private Integer price;

    }

}
