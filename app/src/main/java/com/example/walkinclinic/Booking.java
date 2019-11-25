package com.example.walkinclinic;

public class Booking {

    String clinicId;
    String username;
    String id;
    String serviceId;
    int startTime;
    int endTime;
    String day;                     // "weekday", "weekend"

    public Booking() {

    }

    public Booking (String c, String u, String i, String s, int sT, int eT, String d) {
        clinicId = c;
        username = u;
        id = i;
        serviceId = s;
        startTime = sT;
        endTime = eT;
        day = d;
    }
    public Booking (Booking b) {
        clinicId = b.getClinicId();
        username = b.getUsername();
        id = b.getId();
        serviceId = b.getServiceId();
        startTime = b.getStartTime();
        endTime = b.getEndTime();
        day = b.getDay();
    }

    public String getId() {
        return id;
    }

    public String getClinicId() {
        return clinicId;
    }

    public String getUsername() {
        return username;
    }

    public int getEndTime() {
        return endTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public String getDay() {
        return day;
    }

    public String getServiceId() {
        return serviceId;
    }
}
