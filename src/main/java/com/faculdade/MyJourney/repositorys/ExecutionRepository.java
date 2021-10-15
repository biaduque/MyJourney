package com.faculdade.MyJourney.repositorys;

import com.faculdade.MyJourney.models.Activity;
import com.faculdade.MyJourney.models.Execution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExecutionRepository extends JpaRepository<Execution, Long> {

    List<Execution> findAllByDate(LocalDate date);
}
