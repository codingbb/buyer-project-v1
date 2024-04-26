package com.example.buyer.order;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderResponse {

    @Data
    public static class ListDTO {
        private Integer id;
        private Integer buyQty;
        private String payment;
        private Integer sum;
        private LocalDate createdAt;
        private String name;
        private Integer indexNum;

        @Builder
        public ListDTO(Integer id, Integer buyQty, String payment, Integer sum, LocalDate createdAt, String name, Integer indexNum) {
            this.id = id;
            this.buyQty = buyQty;
            this.payment = payment;
            this.sum = sum;
            this.createdAt = createdAt;
            this.name = name;
            this.indexNum = indexNum;
        }
    }

}
