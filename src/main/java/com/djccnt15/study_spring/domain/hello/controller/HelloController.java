package com.djccnt15.study_spring.domain.hello.controller;

import com.djccnt15.study_spring.domain.hello.model.Hello;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("name", "hello!!");
        return "hello";
    }
    
    @GetMapping("hello-mvc")
    public String helloMvc(
        @RequestParam("name") String name,
        Model model
    ) {
        model.addAttribute("name", name);
        return "hello-template";
    }
    
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello %s".formatted(name);
    }
    
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        return Hello.builder().name(name).build();
    }
}
