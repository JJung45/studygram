package com.studygram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OAuth2Controller {

    @GetMapping("/socialLogin")
    public String socialLogin() {
        return ""; // 추후 login 페이지
    }
}
