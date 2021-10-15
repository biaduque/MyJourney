package com.faculdade.MyJourney.dto;

import com.faculdade.MyJourney.controllers.ActivityController;
import com.faculdade.MyJourney.models.Activity;
import com.faculdade.MyJourney.models.Student;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ActivityDto {
    private String name;
    private int color;

    public ActivityDto(Activity activity) {
        this.name = activity.getName();
        this.color = activity.getColor();
    }

    public String getName() {
        return name;
    }

    public ActivityDto() {
    }

    public int getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public static List<ActivityDto> convert(List<Activity> activities){
        List<ActivityDto> activityDtos = new ArrayList<>();
        for(int x=0;x<activities.size();x++){
            ActivityDto st = new ActivityDto(activities.get(x));
            activityDtos.add(st);
        }
        return activityDtos;
    }
}
