package com.example.walkinclinic;

public class Employee{

    private String clinic;
    private boolean completed;
    private String username;

    public Employee(String clinic, boolean complete, String username){
        this.username = username;
        this.completed = complete;
        this.clinic = clinic;
    }
    public Employee(String username, String clinic) {
        this.username = username;
        this.clinic = clinic;
        if (clinic!=null) {
            completed = true;
        } else {
            completed = false;
        }
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getClinic() {
        return clinic;
    }
    public String getUsername() {
        return username;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public String stringInfo() {
        if (clinic==null && completed) {
            return username+" ; none";
        }else if (clinic==null && !completed) {
            return username+" ; not-completed ; none";
        }
        if (completed) {
            return username+" "+clinic;
        }else {
            return username+" ; not-completed ; "+clinic;
        }
    }
}
