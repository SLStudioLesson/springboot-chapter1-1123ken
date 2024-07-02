package com.example.chapter01test;

import java.time.LocalDateTime;

public class Schedule {
    private int id; // スケジュールのID
    private String name; // スケジュールのタスク名
    private LocalDateTime startDateTime; // スケジュールの開始日時
    private double duration; // スケジュールの所要時間
    private String location; // スケジュールの場所

    public Schedule(int id, String name, LocalDateTime startDateTime, double duration, String location) {
        this.id = id;
        this.name = name;
        this.startDateTime = startDateTime;
        this.duration = duration;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
