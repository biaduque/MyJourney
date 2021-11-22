package com.faculdade.MyJourney.dto;

import com.faculdade.MyJourney.models.Reminder;
import com.faculdade.MyJourney.models.Student;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReminderDto {
    String name;
    String description;
    LocalTime date;

    public ReminderDto() {
    }

    public ReminderDto(Reminder reminder) {
        this.name = reminder.getName();
        this.description = reminder.getDescription();
        this.date = reminder.getDate();
    }

    public static List<ReminderDto> convert(List<Reminder> reminders){
        List<ReminderDto> reminderDtos = new ArrayList<>();
        for(int x=0;x<reminders.size();x++){
            ReminderDto st = new ReminderDto(reminders.get(x));
            reminderDtos.add(st);
        }
        return reminderDtos;
    }
}
