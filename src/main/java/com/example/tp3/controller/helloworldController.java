package com.example.tp3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class helloworldController {
    @GetMapping("/")
    public String greeting() {
        return "home";
    }
}
