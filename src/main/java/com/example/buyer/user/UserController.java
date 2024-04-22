package com.example.buyer.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/join-form")
    public String userJoinForm() {

        return "/user/user-join-form";
    }

    @GetMapping("/comp-join-form")
    public String compJoinForm() {

        return "/comp/comp-join-form";
    }

    @GetMapping("/login-form")
    public String login() {

        return "/user/login";
    }

    @GetMapping("/user-update-form")
    public String userUpdateForm() {

        return "/user/user-update-form";
    }

    @GetMapping("/comp-update-form")
    public String compUpdateForm() {

        return "/comp/comp-update-form";
    }

    @GetMapping("/logout")
    public String logout() {

        return "redirect:/";
    }

}
