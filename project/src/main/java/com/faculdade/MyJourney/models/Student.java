package com.faculdade.MyJourney.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue
    Long id;

    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Activity> listActivity;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reminder> listReminder;

    public Student(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Student() {

    }

    public Student(Student student) {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHistoric(Activity activity) {
        this.listActivity.add(activity);
    }

    public void setReminder(Reminder reminder) {
        this.listReminder.add(reminder);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setReminder(int hour, int minutes){
        Reminder reminder = new Reminder();
        LocalTime time= LocalTime.now();
        reminder.setDate(time);
    }

    public List<Activity> getListActivity() {
        return listActivity;
    }

    public List<Reminder> getListReminder() {
        return listReminder;
    }

    public void setListActivity(List<Activity> listActivity) {
        this.listActivity = listActivity;
    }

    public void setListReminder(List<Reminder> listReminder) {
        this.listReminder = listReminder;
    }
}
