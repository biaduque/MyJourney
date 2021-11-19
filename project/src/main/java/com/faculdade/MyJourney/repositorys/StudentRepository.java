package com.faculdade.MyJourney.repositorys;

import com.faculdade.MyJourney.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findFirstByName(String name);
}
