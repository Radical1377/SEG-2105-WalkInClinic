package com.example.walkinclinic;

public class Patient extends User {

    //class constructor
    public Patient(String username, String password, String first_name, String last_name, String email, boolean needsEncrypt){

        super(username, password, first_name, last_name, email,1, needsEncrypt);
    }

    //patient functionalities to be added in future deliverables...
}
