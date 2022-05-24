package com.example.testapp.controller.professor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/professor")
@RequiredArgsConstructor
public class ProfessorLoginController {
    @GetMapping("login")
    public String readLoginForm() {
        return "/professor/login";
    }
}
