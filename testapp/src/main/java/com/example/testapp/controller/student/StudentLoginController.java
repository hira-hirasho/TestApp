package com.example.testapp.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentLoginController {
    @GetMapping("login")
    public String readLoginForm() {
        return "/student/login";
    }
}
