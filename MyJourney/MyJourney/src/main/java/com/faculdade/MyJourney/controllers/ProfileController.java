package com.faculdade.MyJourney.controllers;

import com.faculdade.MyJourney.Services.StudentService;
import com.faculdade.MyJourney.models.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ProfileController {

    private final StudentService studentService;

    @GetMapping("/profile")
    public String getMes(Model model) {

        Student student = studentService.getStudentFromLogin();
        model.addAttribute("username", student.getName());

        return "profile";
    }

}
