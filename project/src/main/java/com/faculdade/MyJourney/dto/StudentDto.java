package com.faculdade.MyJourney.dto;

import com.faculdade.MyJourney.models.Reminder;
import com.faculdade.MyJourney.models.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDto {
    private String name;
    private String email;
    private String password;

    public StudentDto(Student student) {
        this.name = student.getName();
        this.email = student.getEmail();
        this.password = student.getPassword();
    }

    public StudentDto(Reminder reminder) {
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public StudentDto() {

    }

    public static List<StudentDto> convert(List<Student> students){
        List<StudentDto> studentsData = new ArrayList<>();
        for(int x=0;x<students.size();x++){
            StudentDto st = new StudentDto(students.get(x));
            studentsData.add(st);
        }
        return studentsData;
    }
}
