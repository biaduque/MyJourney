package com.faculdade.MyJourney.models;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class Reminder {
    @Id
    @GeneratedValue
    Long id;
    String name;
    String description;
    LocalTime date;

    @ManyToOne
    private Student student;

    public Reminder(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Reminder() {

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalTime getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalTime date) {
        this.date = date;
    }
}
