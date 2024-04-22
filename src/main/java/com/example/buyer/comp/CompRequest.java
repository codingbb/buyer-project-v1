package com.example.buyer.comp;

import lombok.Data;

import java.time.LocalDate;

public class CompRequest {

    @Data
    public static class JoinDTO {
        private String username;
        private String password;
        private String compName;
        private String name;
        private String compNum;
        private String phone;
        private LocalDate birth;
        private String address;
        private Integer role;
    }

}
