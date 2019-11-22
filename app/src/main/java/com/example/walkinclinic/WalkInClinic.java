package com.example.walkinclinic;

import java.util.ArrayList;

public class WalkInClinic {
    private String id;
    private String _name;
    private String _address;
    private int _openingHourWeekDay;
    private int _closingHourWeekDay;
    private int _openingHourWeekEnd;
    private int _closingHourWeekEnd;
    private float _rate;
    private int _waitingTime;
    private String phoneNumber;
    private ArrayList<String> insuranceType = new ArrayList<String>();;
    private ArrayList<String> paymentMethod = new ArrayList<String>();;


    public WalkInClinic(){
        _name = null;
    }

    public WalkInClinic (String name, String address, int ohD, int chD, int ohE, int chE){
        this._name = name;
        this._address = address;
        this._openingHourWeekDay = ohD;
        this._closingHourWeekDay = chD;
        this._openingHourWeekEnd = ohE;
        this._closingHourWeekEnd = chE;
        this.insuranceType = new ArrayList<String>();
        this.paymentMethod = new ArrayList<String>();
        this._rate = 0;
        this._waitingTime = 0;
    }
    public WalkInClinic (String id, String name, String address, int ohD, int chD, int ohE, int chE){
        this._name = name;
        this.id = id;
        this._address = address;
        this._openingHourWeekDay = ohD;
        this._closingHourWeekDay = chD;
        this._openingHourWeekEnd = ohE;
        this._closingHourWeekEnd = chE;
        this.insuranceType = new ArrayList<String>();
        this.paymentMethod = new ArrayList<String>();
        this._rate = 0;
        this._waitingTime = 0;
    }

    public WalkInClinic (String id, String name, String address, int ohD, int chD, int ohE, int chE, ArrayList<String> insurance, ArrayList<String> payment){
        this._name = name;
        this.id = id;
        this._address = address;
        this._openingHourWeekDay = ohD;
        this._closingHourWeekDay = chD;
        this._openingHourWeekEnd = ohE;
        this._closingHourWeekEnd = chE;
        this.insuranceType = insurance;
        this.paymentMethod = payment;
        this._rate = 0;
        this._waitingTime = 0;
    }

    public WalkInClinic (String id, String name, String address, int ohD, int chD, int ohE, int chE, int rate, int wait, ArrayList<String> insurance, ArrayList<String> payment){
        this._name = name;
        this.id = id;
        this._address = address;
        this._openingHourWeekDay = ohD;
        this._closingHourWeekDay = chD;
        this._openingHourWeekEnd = ohE;
        this._closingHourWeekEnd = chE;
        this.insuranceType = insurance;
        this.paymentMethod = payment;
        this._rate = rate;
        this._waitingTime = wait;
    }

    // getters
    public String getId(){
        return this.id;
    }
    public String get_name() {
        return this._name;
    }
    public String get_address() {
        return this._address;
    }
    public int get_openingHourWeekDay() {
        return _openingHourWeekDay;
    }
    public int get_closingHourWeekDay() {
        return _closingHourWeekDay;
    }
    public int get_openingHourWeekEnd() {
        return _openingHourWeekEnd;
    }
    public int get_closingHourWeekEnd() {
        return _closingHourWeekEnd;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public ArrayList<String> getInsuranceType() {
        return insuranceType;
    }
    public ArrayList<String> getPaymentMethod() {
        return paymentMethod;
    }
    public int get_waitingTime() {
        return _waitingTime;
    }
    public float get_rate() {
        return _rate;
    }

    // TO MODIFY
    public String fullHours(){
        String str = "Monday to Friday: "+this._openingHourWeekDay +"-"+ this._closingHourWeekDay+", Saturday/Sunday: "+this._openingHourWeekEnd +"-"+ this._closingHourWeekEnd;
        return str;
     }


    // setters
    public void set_name(String name) {
        this._name = name;
    }
    public void set_address(String address) {
        this._address = address;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setInsuranceType(ArrayList<String> insuranceType) {
        this.insuranceType = insuranceType;
    }
    public void setPaymentMethod(ArrayList<String> paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public void set_rate(float _rate) {
        this._rate = _rate;
    }
    public void set_openingHourWeekEnd(int _openingHourWeekEnd) {
        this._openingHourWeekEnd = _openingHourWeekEnd;
    }
    public void set_closingHourWeekEnd(int _closingHourWeekEnd) {
        this._closingHourWeekEnd = _closingHourWeekEnd;
    }
    public void set_openingHourWeekDay(int _openingHourWeekDay) {
        this._openingHourWeekDay = _openingHourWeekDay;
    }
    public void set_closingHourWeekDay(int _closingHourWeekDay) {
        this._closingHourWeekDay = _closingHourWeekDay;
    }

    public String stringInfo() {
        return _name+" "+id+" "+_address+" "+_openingHourWeekDay+" "+_closingHourWeekDay+" "+_openingHourWeekEnd+" "+_closingHourWeekEnd+" "+_rate+" "+_waitingTime;
    }
}
