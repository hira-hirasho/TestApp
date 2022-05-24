package com.example.testapp.controller.student;

import com.example.testapp.repository.StudentMasterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentIndexController {
    @Autowired
    private final StudentMasterRepository repository;

    @GetMapping("")
    public String readStudentPage(Authentication loginUser, Model model) {
        model.addAttribute("student", repository.findByLoginId(loginUser.getName()));
        return "/student/index";
    }
}
