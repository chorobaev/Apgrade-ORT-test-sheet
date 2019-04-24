package com.example.apgrate.model;

import java.util.ArrayList;

public class Schedule {

    private ArrayList<Integer> schedule;
    private int index;

    public Schedule() {
        schedule = new ArrayList<>();
        index = 0;
        schedule.add(1800);
        schedule.add(300);
        schedule.add(3600);
        schedule.add(600);
        schedule.add(1800);
        schedule.add(300);
        schedule.add(3600);
        schedule.add(300);
        schedule.add(2100);
    }

    public void next() {
        if (index < schedule.size()) {
            index++;
        }
    }

    public int getScheduleIndex() {
        return schedule.get(index);
    }
}
