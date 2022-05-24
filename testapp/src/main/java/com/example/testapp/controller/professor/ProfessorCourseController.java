package com.example.testapp.controller.professor;

import com.example.testapp.model.Professor;
import com.example.testapp.repository.CourseMasterRepository;
import com.example.testapp.repository.ProfessorMasterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/professor")
@RequiredArgsConstructor
public class ProfessorCourseController {
    @Autowired
    private final ProfessorMasterRepository pRepository;

    @Autowired
    private final CourseMasterRepository cRepository;

    @GetMapping("/course")
    public String readProfessorPage(Authentication loginUser, Model model) {
        Professor professor = pRepository.findByLoginId(loginUser.getName());
        model.addAttribute("professor", professor);
        model.addAttribute("courseList", cRepository.findByProfessor(professor));
        return "/professor/course";
    }
}
