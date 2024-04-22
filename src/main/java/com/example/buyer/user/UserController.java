package com.example.buyer.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final HttpSession session;

    //회원가입
    @PostMapping("/join")
    public String userJoin(UserRequest.JoinDTO requestDTO) {
        userService.save(requestDTO);
        return "redirect:/";
    }

    @GetMapping("/user-join-form")
    public String userJoinForm() {

        return "/user/user-join-form";
    }

    @GetMapping("/login-form")
    public String login() {

        return "/user/login";
    }

    @GetMapping("/user-update-form")
    public String userUpdateForm() {

        return "/user/user-update-form";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

}
