package com.example.walkinclinic;

import com.example.walkinclinic.data.WorkHours;

public class Employee{

    private String _clinic;
    private boolean _completed;
    private String _username;
    private WorkHours[] _workHours; //separate class for the workhours of the employee

    public Employee(){
        this._username = null;
        this._completed = false;
        this._clinic = null;
        this._workHours=new WorkHours[7]; //7 days a week, so 7 sets of workHours
        //have to initialise it (not always created by user when registering
        for (int i=0; i<7; i++) {
            WorkHours workHours=new WorkHours();
            String day=workHours.getDayArray(i); //what day initialise
            _workHours[i]=workHours;
            _workHours[i].setDay(day);
        }

    }

    public Employee(String clinic, boolean complete, String username){
        this._username = username;
        this._completed = complete;
        this._clinic = clinic;
        this._workHours=new WorkHours[7];
        //have to initialise it (not always created by user when registering
        for (int i=0; i<7; i++) {
            WorkHours workHours=new WorkHours();
            String day=workHours.getDayArray(i); //what day initialise
            _workHours[i]=workHours;
            _workHours[i].setDay(day);
        }
    }
    public Employee(String username, String clinic) {
        this._username = username;
        this._clinic = clinic;
        if (clinic!=null) {
            _completed = true;
        } else {
            _completed = false;
        }
        //have to initialise it (not always created by user when registering
        this._workHours=new WorkHours[7];
        for (int i=0; i<7; i++) {
            WorkHours workHours=new WorkHours();
            String day=workHours.getDayArray(i); //what day initialise
            this._workHours[i]=workHours;
            this._workHours[i].setDay(day);
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
    public WorkHours[] getWorkHours() {return  _workHours;}
    public WorkHours getDayHours(String day) { //working hours for specific day
        WorkHours result=null;
        for (int i=0; i<7; i++) {
            if (_workHours[i].getDay()==day) {result= _workHours[i];}
        }
        return result;
    }

    public void setClinic(String clinic) {
        this._clinic = clinic;
    }
    public void setCompleted(boolean completed) {
        this._completed = completed;
    }
    public void set_workHours(WorkHours workHours) {
        int i=0;
        while(i<7) {
            if (_workHours[i]==null) {this._workHours[i]=workHours;}
            else {i++;}
        }
    }
    public void set_dayHours(String day, WorkHours workHours) {
        int i=0;
        while (_workHours[i].getDay()!=day) {
            i++;
        }
        _workHours[i]=workHours;
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
