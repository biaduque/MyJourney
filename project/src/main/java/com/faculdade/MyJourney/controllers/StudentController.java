package com.faculdade.MyJourney.controllers;

import com.faculdade.MyJourney.dto.StudentDto;
import com.faculdade.MyJourney.models.Student;
import com.faculdade.MyJourney.repositorys.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estudante")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/dados")
    public List<StudentDto> prtStudent(){
        List<Student> students = studentRepository.findAll();
        return StudentDto.convert(students);
    }

    @PostMapping
    @Transactional
    public void register(@RequestBody Student student){
        studentRepository.save(student);
    }
}
