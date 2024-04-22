package com.example.buyer.comp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class CompController {
    private final CompService compService;



    @GetMapping("/comp-join-form")
    public String compJoinForm() {

        return "/comp/comp-join-form";
    }


    @GetMapping("/comp-update-form")
    public String compUpdateForm() {

        return "/comp/comp-update-form";
    }

}
