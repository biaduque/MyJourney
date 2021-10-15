package com.faculdade.MyJourney.repositorys;

import com.faculdade.MyJourney.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
