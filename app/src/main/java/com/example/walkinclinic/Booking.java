package com.example.walkinclinic;

public class Booking {

    private String clinicId;
    private String username;
    private String id;
    private String serviceId;
    private String startTime;
    private String date;
    private WalkInClinic clinic;

    public Booking() {

    }

    public Booking (String c, String u, String i, String s, String st, String d) {
        clinicId = c;
        username = u;
        id = i;
        serviceId = s;
        startTime = st;
        date = d;

    }
    public Booking (String c, String u, String i, String s, String st, String d, WalkInClinic cl) {
        clinicId = c;
        username = u;
        id = i;
        serviceId = s;
        startTime = st;
        date = d;
        clinic = cl;

    }
    public Booking (Booking b) {
        clinicId = b.clinicId;
        username = b.username;
        id = b.id;
        serviceId = b.serviceId;
        startTime = b.startTime;
        date = b.date;
    }

    public String getId() {
        return id;
    }

    public WalkInClinic getClinic() {
        return clinic;
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

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }
}
