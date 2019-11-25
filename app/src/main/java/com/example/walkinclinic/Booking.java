package com.example.walkinclinic;

public class Booking {

    private String clinicId;
    private String username;
    private String id;
    private String serviceId;
    private int startTimeHour;
    private int endTimeHour;
    private int startTimeMinute;
    private int endTimeMinute;
    private int day;
    private int month;
    private int year;

    public Booking() {

    }

    public Booking (String c, String u, String i, String s, int sTh, int sTM, int eTh, int eTM, int d, int m, int y) {
        clinicId = c;
        username = u;
        id = i;
        serviceId = s;
        startTimeHour = sTh;
        startTimeMinute =  sTM;
        endTimeHour = eTh;
        endTimeMinute = eTM;
        day = d;
        month = m;
        year = y;

    }
    public Booking (Booking b) {
        clinicId = b.clinicId;
        username = b.username;
        id = b.id;
        serviceId = b.serviceId;
        startTimeHour = b.startTimeHour;
        startTimeMinute = b.startTimeMinute;
        endTimeHour = b.endTimeHour;
        endTimeMinute = b.endTimeMinute;
        day = b.day;
        month = b.month;
        year = b.year;
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

    public String getServiceId() {
        return serviceId;
    }
}
