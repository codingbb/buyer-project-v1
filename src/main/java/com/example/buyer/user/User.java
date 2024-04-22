package com.example.buyer.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "user_tb")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;        //아이디

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;            //이름(성명)

    @Column(nullable = false)
    private String phone;           //전화번호

    @Column(nullable = false)
    private LocalDateTime birth;    //생년월일

    @Column(nullable = false)
    private String address;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public User(Integer id, String username, String password, String name, String phone, LocalDateTime birth, String address, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.birth = birth;
        this.address = address;
        this.createdAt = createdAt;
    }
}
