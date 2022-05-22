package com.example.testapp.controller.student;

import com.example.testapp.model.Student;
import com.example.testapp.repository.StudentMasterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/student/register")
@SessionAttributes(names = "student")
public class StudentRegistController {
    @Autowired
    private final StudentMasterRepository repository;
    private final PasswordEncoder passwordEncoder;

    @ModelAttribute
    public Student setUpStudent() {
        return new Student();
    }

    @GetMapping("register")
    public String readRegistForm() {
        return "/student/register/register";
    }

    @PostMapping("register")
    public String registConfirm(@ModelAttribute Student student) {
        return "/student/register/confirm";
    }

    @PostMapping("confirm")
    public String regist(@ModelAttribute Student student, SessionStatus sessionStatus) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        repository.save(student);
        sessionStatus.setComplete();
        return "/student/register/complete";
    }
}
