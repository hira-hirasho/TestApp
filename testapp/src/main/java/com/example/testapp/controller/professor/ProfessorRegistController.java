package com.example.testapp.controller.professor;

import com.example.testapp.model.Professor;
import com.example.testapp.repository.ProfessorMasterRepository;

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
@RequestMapping("/professor/register")
@SessionAttributes(names = "professor")
public class ProfessorRegistController {
    @Autowired
    private final ProfessorMasterRepository repository;
    private final PasswordEncoder passwordEncoder;

    @ModelAttribute
    public Professor setUpProfessor() {
        return new Professor();
    }

    @GetMapping("register")
    public String readRegistForm() {
        return "/professor/register/register";
    }

    @PostMapping("register")
    public String registConfirm(@ModelAttribute Professor professor) {
        return "/professor/register/confirm";
    }

    @PostMapping("confirm")
    public String regist(@ModelAttribute Professor professor, SessionStatus sessionStatus) {
        professor.setPassword(passwordEncoder.encode(professor.getPassword()));
        repository.save(professor);
        sessionStatus.setComplete();
        return "/professor/register/complete";
    }
}
