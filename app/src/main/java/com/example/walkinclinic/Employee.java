package com.example.walkinclinic;

public class Employee{

    private String _clinic;
    private boolean _completed;
    private String _username;

    public Employee(){
        this._username = null;
        this._completed = false;
        this._clinic = null;
    }

    public Employee(String clinic, boolean complete, String username){
        this._username = username;
        this._completed = complete;
        this._clinic = clinic;
    }
    public Employee(String username, String clinic) {
        this._username = username;
        this._clinic = clinic;
        if (clinic!=null) {
            _completed = true;
        } else {
            _completed = false;
        }
    }

    public boolean isCompleted() {
        return _completed;
    }

    public String getClinic() {
        return _clinic;
    }
    public String getUsername() {
        return _username;
    }

    public void setClinic(String clinic) {
        this._clinic = clinic;
    }
    public void setCompleted(boolean completed) {
        this._completed = completed;
    }
    public String stringInfo() {
        if (_clinic==null && _completed) {
            return _username+" ; none";
        }else if (_clinic==null && !_completed) {
            return _username+" ; not-completed ; none";
        }
        if (_completed) {
            return _username+" "+_clinic;
        }else {
            return _username+" ; not-completed ; "+_clinic;
        }
    }
}
