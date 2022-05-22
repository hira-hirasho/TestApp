package com.example.testapp.controller.student;

import com.example.testapp.model.Student;
import com.example.testapp.repository.StudentMasterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/student")
@SessionAttributes(names = "student")
@RequiredArgsConstructor
public class StudentLoginController {
    @Autowired
    private final StudentMasterRepository repository;

    @ModelAttribute
    public Student setUpStudent() {
        return new Student();
    }

    @GetMapping("login")
    public String readLoginForm() {
        return "/student/login";
    }

    @GetMapping("")
    public String login(Authentication loginUser, Model model) {
        model.addAttribute("student", repository.findByLoginId(loginUser.getName()));
        return "/student/index";
    }

    @PostMapping("logout")
    public String logout(@ModelAttribute Student student, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "/student/login";
    }
}
