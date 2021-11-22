package com.faculdade.MyJourney.repositorys;

import com.faculdade.MyJourney.models.Activity;
import com.faculdade.MyJourney.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findAllByStudent(Student student);
}
