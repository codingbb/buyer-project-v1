package com.example.buyer.comp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class CompController {
    private final CompService compService;

    @PostMapping("/comp-join")
    public String join(CompRequest.JoinDTO requestDTO) {
        compService.save(requestDTO);
        return "redirect:/";
    }

    @GetMapping("/comp-join-form")
    public String compJoinForm() {

        return "/comp/comp-join-form";
    }


    @GetMapping("/comp-update-form")
    public String compUpdateForm() {

        return "/comp/comp-update-form";
    }

}
