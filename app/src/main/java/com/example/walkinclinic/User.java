package com.example.walkinclinic;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class User {

    protected String _username; // primary identifier
    protected String password; // encrypted using SHA-256 encryption
    protected String first_name, last_name; // real first and last name
    protected String _email; // email address
    protected boolean _needEncrypt = false;
    protected int _role; // 0 = admin, 1 = employee, 2 = patient

    //class constructors
    public User() {
        this.first_name=null;
        this.last_name=null;
        this._email=null;
    }
    public User(String first_name, String last_name, String email){

        this.first_name = first_name;
        this.last_name = last_name;
        this._email = email;
    }

    public User(String username, String password, String first_name, String last_name, String email, int role, boolean needEncrypt){

        this._username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this._email = email;
        this._role = role;

        //exception handling
        try {
            if(needEncrypt)
                this.password = Sha256.encrypt(password); //encrypts given plain password
            else
                this.password = password;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this._needEncrypt = false;
    }

    //setter methods
    public void setUsername(String username){
        this._username = username;
    }
    public void setPassword(String password, boolean encrypt){
        try {
            if(encrypt)
                this.password = Sha256.encrypt(password); //encrypts given plain password
            else
                this.password = password;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void setFullName(String first_name, String last_name){
        this.first_name = first_name;
        this.last_name = last_name;
    }
    public void setEmail(String email){
        this._email = email;
    }

    //getter methods
    public String getUsername(){
        return this._username;
    }
    public String getPassword(){
        return this.password;
    }
    public String getFirst_name(){
        return this.first_name;
    }
    public String getLast_name(){
        return this.last_name;
    }
    public String getEmail(){
        return this._email;
    }
    public int getRole() { return this._role; }

    //info printer method
    public void printInfo(){

        System.out.println("User: " + this._username);
        System.out.println("Password: " + this.password);
        System.out.println("Full Name: " + this.first_name + " " + this.last_name);
        System.out.println("Email: " + this._email);

        switch(this._role){
            case 0:{
                System.out.println("Role: Admin");
                break;
            }
            case 1:{
                System.out.println("Role: Employee");
                break;
            }
            case 2:{
                System.out.println("Role: Patient");
                break;
            }
            default:{
                System.out.println("Role: Unknown");
                break;
            }
        }

    }

    public String stringInfo() {
        return _username+" "+password+" "+first_name+" "+last_name+" "+_email;
    }

}
