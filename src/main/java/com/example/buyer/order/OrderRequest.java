package com.example.buyer.order;

import lombok.Data;

public class OrderRequest {

    @Data
    public static class OrderDTO {
        private String name;
        private String address;
        private String phone;

    }

}
