package com.example.testapp.controller.professor;

import com.example.testapp.model.Professor;
import com.example.testapp.repository.CourseMasterRepository;
import com.example.testapp.repository.ProfessorMasterRepository;

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
@RequestMapping("/professor")
@SessionAttributes(names = "professor")
@RequiredArgsConstructor
public class ProfessorLoginController {
    @Autowired
    private final ProfessorMasterRepository pRepository;

    @Autowired
    private final CourseMasterRepository cRepository;

    @ModelAttribute
    public Professor setUpProfessor() {
        return new Professor();
    }

    @GetMapping("login")
    public String readLoginForm() {
        return "/professor/login";
    }

    @GetMapping("")
    public String readProfessorPage(Authentication loginUser, Model model) {
        Professor professor = pRepository.findByLoginId(loginUser.getName());
        model.addAttribute("professor", professor);
        model.addAttribute("courseList", cRepository.findByProfessor(professor));
        return "/professor/index";
    }

    @PostMapping("logout")
    public String logout(@ModelAttribute Professor professor, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "/professor/login";
    }
}
