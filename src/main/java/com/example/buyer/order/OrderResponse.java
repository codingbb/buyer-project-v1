package com.example.buyer.order;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

public class OrderResponse {

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

        //주문한 상품 수량 //order
        private Integer buyQty;
        private Integer sum;

    }

    //내 구매목록 DTO
    @Data
    public static class ListDTO {
        private Integer id;
        private Integer userId;
        private Integer buyQty;
        private String payment;
        private Integer sum;
        private LocalDate createdAt;
        private String name;
        private Integer indexNum;

        @Builder
        public ListDTO(Integer id, Integer userId, Integer buyQty, String payment, Integer sum, LocalDate createdAt, String name, Integer indexNum) {
            this.id = id;
            this.userId = userId;
            this.buyQty = buyQty;
            this.payment = payment;
            this.sum = sum;
            this.createdAt = createdAt;
            this.name = name;
            this.indexNum = indexNum;
        }
    }

}
