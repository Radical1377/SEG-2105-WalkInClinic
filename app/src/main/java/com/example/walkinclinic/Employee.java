package com.example.walkinclinic;

public class Employee{

    private String _clinic;
    private boolean _completed;
    private String _username;
    private WorkingHours mon;
    private WorkingHours tues;
    private WorkingHours wed;
    private WorkingHours thurs;
    private WorkingHours fri;
    private WorkingHours sat;
    private WorkingHours sun;


    public Employee(){
        this._username = null;
        this._completed = false;
        this._clinic = null;
        this.mon = new WorkingHours(0,0);
        this.tues = new WorkingHours(0,0);
        this.wed = new WorkingHours(0,0);
        this.thurs = new WorkingHours(0,0);
        this.fri = new WorkingHours(0,0);
        this.sat = new WorkingHours(0,0);
        this.sun = new WorkingHours(0,0);

    }

    public Employee(String clinic, boolean complete, String username) {
        this._username = username;
        this._completed = complete;
        this._clinic = clinic;
        this.mon = new WorkingHours(0, 0);
        this.tues = new WorkingHours(0, 0);
        this.wed = new WorkingHours(0, 0);
        this.thurs = new WorkingHours(0, 0);
        this.fri = new WorkingHours(0, 0);
        this.sat = new WorkingHours(0, 0);
        this.sun = new WorkingHours(0, 0);
    }

    public Employee(String username, String clinic) {
        this._username = username;
        this._clinic = clinic;
        if (clinic!=null) {
            _completed = true;
        } else {
            _completed = false;
        }
        this.mon = new WorkingHours(0,0);
        this.tues = new WorkingHours(0,0);
        this.wed = new WorkingHours(0,0);
        this.thurs = new WorkingHours(0,0);
        this.fri = new WorkingHours(0,0);
        this.sat = new WorkingHours(0,0);
        this.sun = new WorkingHours(0,0);
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
    public WorkingHours getMon() {
        return mon;
    }
    public WorkingHours getTues() {
        return tues;
    }
    public WorkingHours getWed() {
        return wed;
    }
    public WorkingHours getThurs() {
        return thurs;
    }
    public WorkingHours getFri() {
        return fri;
    }
    public WorkingHours getSat() {
        return sat;
    }
    public WorkingHours getSun() {
        return sun;
    }


    public void set_username(String _username) {
        this._username = _username;
    }
    public void setClinic(String clinic) {
        this._clinic = clinic;
    }
    public void setCompleted(boolean completed) {
        this._completed = completed;
    }
    public void setMon(WorkingHours mon) {
        this.mon = mon;
    }
    public void setTues(WorkingHours tues) {
        this.tues = tues;
    }
    public void setWed(WorkingHours wed) {
        this.wed = wed;
    }
    public void setThurs(WorkingHours thurs) {
        this.thurs = thurs;
    }
    public void setFri(WorkingHours fri) {
        this.fri = fri;
    }
    public void setSat(WorkingHours sat) {
        this.sat = sat;
    }
    public void setSun(WorkingHours sun) {
        this.sun = sun;
    }

    public String toString(){
        return _username+" "+_clinic+" "+mon.toString()+" "+tues.toString()+" "+wed.toString()+" "
                +thurs.toString()+" "+fri.toString()+" "+sat.toString()+" "+sun.toString();
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
