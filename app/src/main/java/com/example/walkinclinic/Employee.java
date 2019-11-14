package com.example.walkinclinic;

public class Employee{

    private String clinic = null;
    private String username;
    private boolean completed;

    public Employee(String username, String clinic) {
        this.username = username;
        this.clinic = clinic;
        if (clinic!=null) {

            completed = true;
        } else {

            completed = false;
        }
    }
    public Employee(User user, String clinic){
        username = user.getUsername();
        this.clinic = clinic;
        if (clinic!=null) {

            completed = true;
        } else {

            completed = false;
        }
    }
    public Employee(User user){
        username = user.getUsername();
        this.completed = false;
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
        if (clinic==null) {
            return username+" ; none";
        }
        return username+" "+clinic;
    }
}
