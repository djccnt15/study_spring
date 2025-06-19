package com.djccnt15.study_spring.domain.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    /**
     * temporal redirect to swagger open api
     */
    @GetMapping
    public String home() {
        return "redirect:swagger-ui/index.html";
    }
}
