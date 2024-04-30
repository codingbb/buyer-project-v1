package com.example.buyer.orderProduct;

import lombok.Data;

public class OrderProductRequest {

    //order save용 DTO
    @Data
    public static class SaveDTO {
        //product 들고 오는 부분
        private Integer productId;
        private String pName;
        private Integer buyQty;    //선택한 수량
        private Integer orderId;  //계산된 가격

    }


}
