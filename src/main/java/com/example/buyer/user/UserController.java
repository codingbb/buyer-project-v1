package com.example.buyer.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/join-form")
    public String joinForm() {

        return "/user/join-form";
    }

    @GetMapping("/login")
    public String login() {

        return "/user/login";
    }

}
