package com.example.walkinclinic;

import androidx.annotation.NonNull;

public class Review {
    private String id;
    private String username;
    private String clinicId;
    private int rating;
    private String comment;

    public Review(String u, String c, int r, String co) {
        username = u;
        clinicId = c;
        rating = r;
        comment = co;
    }

    public Review(String tid, String u, String c, int r, String co) {
        id = tid;
        username = u;
        clinicId = c;
        rating = r;
        comment = co;
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

    public String getId() {
        return id;
    }
}
