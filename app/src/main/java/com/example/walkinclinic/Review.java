package com.example.walkinclinic;

import androidx.annotation.NonNull;

public class Review {
    private String clinicId;
    private String comment;
    private String id;
    private int rating;
    private String username;

    public Review(){
    }

    public Review(String clinic, String comm, String i, int r, String u) {
        clinicId = clinic;
        comment = comm;
        id = i;
        rating = r;
        username = u;
    }

    public int getRating() {
        return rating;
    }

    public String getUsername() {
        return username;
    }

    public String getComment() {
        return comment;
    }

    public String getClinicId() {
        return clinicId;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    public String stringInfo() {
        return "clinicid:"+clinicId+" comment:"+comment+" id:"+id+" rating:"+rating+" username:"+username;
    }

    public String getId() {
        return id;
    }
}
