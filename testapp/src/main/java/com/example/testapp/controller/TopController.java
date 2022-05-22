package com.example.testapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopController {
    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }
}
