package com.example.walkinclinic;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Admin extends User {

    //class constructor
    public Admin(String first_name, String last_name, String email){

        super(first_name,last_name,email);

        this._username = "admin";
        this._role = 0;

        try {
            this.password = Sha256.encrypt("5T5ptQ");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    //admin functionalities to be added in future deliverables...
}
