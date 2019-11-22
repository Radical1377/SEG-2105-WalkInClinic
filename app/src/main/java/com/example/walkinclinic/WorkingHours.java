package com.example.walkinclinic;

public class WorkingHours {
    private double start;
    private double end;
    private String day;

    public WorkingHours(String day, double start, double end) {
        this.day=day;
        this.start=start;
        this.end=end;
    }

    public double getEnd() {
        return end;
    }
    public double getStart() {
        return start;
    }
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
    public void setEnd(double end) {
        this.end = end;
    }
    public void setStart(double start) {
        this.start = start;
    }
}
