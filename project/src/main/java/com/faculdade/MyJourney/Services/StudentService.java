package com.faculdade.MyJourney.Services;

import com.faculdade.MyJourney.models.Student;
import com.faculdade.MyJourney.repositorys.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Student getStudent(String name) {
        Optional<Student> student = studentRepository.findFirstByName(name);
        if (student.isEmpty()) {
            Student newStudent = new Student();
            newStudent.setName(name);
            return studentRepository.save(newStudent);
        } else {
            return student.get();
        }
    }

    public Student getStudentFromLogin() {
        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext()
                .getAuthentication();

        final Principal principal = (Principal) authentication.getPrincipal();

        return getStudent(principal.getName());
    }
}
