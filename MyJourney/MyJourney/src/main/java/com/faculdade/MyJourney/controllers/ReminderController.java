package com.faculdade.MyJourney.controllers;

import com.faculdade.MyJourney.dto.ReminderDto;
import com.faculdade.MyJourney.models.Reminder;
import com.faculdade.MyJourney.repositorys.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lembretes")
public class ReminderController {
    @Autowired
    private ReminderRepository reminderRepository;

    @GetMapping("/dados")
    public List<ReminderDto> prtReminders(){
        List<Reminder> reminders = new ArrayList<>();
        return ReminderDto.convert(reminders);
    }

    @PostMapping
    @Transactional
    public void register(@RequestBody Reminder reminder){
        reminderRepository.save(reminder);
    }
}
