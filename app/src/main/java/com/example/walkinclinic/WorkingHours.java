package com.example.walkinclinic;

public class WorkingHours {
    private double start;
    private double end;

    public WorkingHours() {
        this.start=0;
        this.end=0;
    }

    public WorkingHours(double start, double end) {
        this.start=start;
        this.end=end;
    }

    public double getEnd() {
        return end;
    }
    public double getStart() {
        return start;
    }

    public void setEnd(double end) {
        this.end = end;
    }
    public void setStart(double start) {
        this.start = start;
    }

    public String toString(){
        return start+" : "+end;
    }
}
