package com.example.testapp.controller.student;

import com.example.testapp.repository.CourseMasterRepository;

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
public class StudentCourseController {
    @Autowired
    private final CourseMasterRepository repository;

    @GetMapping("course")
    public String readCoursePage(Authentication loginUser, Model model) {
        model.addAttribute("courseList", repository.findAll());
        return "/student/course";
    }
}
