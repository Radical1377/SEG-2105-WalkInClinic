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


    public Review(String c, String co, String i, int r, String u) {
        clinicId = c;
        comment = co;
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
        return clinicId+" "+comment+" "+id+" "+rating+" "+username;
    }

    public String getId() {
        return id;
    }
}
