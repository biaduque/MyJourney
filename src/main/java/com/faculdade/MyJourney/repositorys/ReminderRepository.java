package com.faculdade.MyJourney.repositorys;

import com.faculdade.MyJourney.models.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder,Long> {
}
