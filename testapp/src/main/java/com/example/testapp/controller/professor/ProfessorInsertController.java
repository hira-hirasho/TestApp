package com.example.testapp.controller.professor;

import com.example.testapp.model.Course;
import com.example.testapp.repository.CourseMasterRepository;
import com.example.testapp.repository.ProfessorMasterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/professor/insert")
@SessionAttributes(names = "course")
public class ProfessorInsertController {
    @Autowired
    private final CourseMasterRepository cRepository;

    @Autowired
    private final ProfessorMasterRepository pRepository;

    @ModelAttribute
    public Course setUpCourse() {
        return new Course();
    }

    @GetMapping("insert")
    public String readInsertForm() {
        return "/professor/insert/insert";
    }

    @PostMapping("insert")
    public String insertConfirm(@ModelAttribute Course course) {
        return "/professor/insert/confirm";
    }

    @PostMapping("confirm")
    public String insert(Authentication loginUser, @ModelAttribute Course course, SessionStatus sessionStatus) {
        course.setProfessor(pRepository.findByLoginId(loginUser.getName()));
        cRepository.save(course);
        sessionStatus.setComplete();
        return "redirect:/professor?success";
    }
}
