package com.faculdade.MyJourney.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Student {

    @Id
    @GeneratedValue
    Long id;

    private String name;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Activity> listActivity;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reminder> listReminder;

    public void setHisto1ric(Activity activity) {
        if (listActivity != null) {
            this.listActivity.add(activity);
        } else {
            setListActivity(List.of(activity));
        }
    }

    public void setReminder(Reminder reminder) {
        this.listReminder.add(reminder);
    }

    public void setReminder(int hour, int minutes) {
        Reminder reminder = new Reminder();
        LocalTime time = LocalTime.now();
        reminder.setDate(time);
    }

}
