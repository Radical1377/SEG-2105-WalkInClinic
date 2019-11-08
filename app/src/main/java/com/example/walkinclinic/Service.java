package com.example.walkinclinic;

public class Service {
    private String id;
    private String _name;
    private int _appropriateStaff; // 0 = doctor, 1 = nurse, 2 = staff



    public Service(){
    }

    public Service(String name, int staff){
        this._name = name;
        this._appropriateStaff = staff;

    }
    public Service(String id, String name, int staff){
        this.id = id;
        this._name = name;
        this._appropriateStaff = staff;

    }

    // getters
    public String getId() {
        return id;
    }
    public String getName() {
        return _name;
    }
    public int getAppropriateStaff() {
        return _appropriateStaff;
    }

    // setters
    public void setName(String name) {
        this._name = name;
    }
    public void setAppropriateStaff(int appropriateStaff) {
        this._appropriateStaff = appropriateStaff;
    }

    public String stringInfo() {
        return id+" "+_name+" "+_appropriateStaff;
    }
}
