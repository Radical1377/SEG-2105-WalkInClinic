package com.example.walkinclinic;

public class WalkInClinic {
    private String id;
    private String _name;
    private String _address;
    private int _openingHour;
    private int _closingHour;
    private float _rate;
    private int _waitingTime;
    //private int[] typeServices; // ??????

    //private static int ID_GENERATOR=1;

    public WalkInClinic(){
        _name = null;
    }

    public WalkInClinic (String name, String address, int oh, int ch){
        this._name = name;
        this._address = address;
        this._openingHour = oh;
        this._closingHour = ch;
        this._rate = 0;
        this._waitingTime = 0;
    }
    public WalkInClinic (String id, String name, String address, int oh, int ch){
        this._name = name;
        this.id = id;
        this._address = address;
        this._openingHour = oh;
        this._closingHour = ch;
        this._rate = 0;
        this._waitingTime = 0;
    }

    public WalkInClinic (String id, String name, String address, int oh, int ch, int rate, int wait){
        this._name = name;
        this.id = id;
        this._address = address;
        this._openingHour = oh;
        this._closingHour = ch;
        this._rate = rate;
        this._waitingTime = wait;
    }


    // getters
    public String getId(){
        return this.id;
    }
    public String getName() {
        return this._name;
    }
    public float getRate() {
        return this._rate;
    }
    public int getClosingHour() {
        return this._closingHour;
    }
    public int getOpeningHour() {
        return this._openingHour;
    }
    public int getWaitingTime() {
        return this._waitingTime;
    }
    public String getAddress() {
        return this._address;
    }
//   // public static int getIdGenerator() {
//        return ID_GENERATOR;
//    }

    // setters
    public void setName(String name) {
        this._name = name;
    }
    public void setAddress(String address) {
        this._address = address;
    }
    public void setOpeningHour(int openingHour) {
        this._openingHour = openingHour;
    }
    public void setClosingHour(int closingHour) {
        this._closingHour = closingHour;
    }
    public void setRate(float rate) {
        this._rate = rate;
    }
    public void setWaitingTime(int waitingTime) {
        this._waitingTime = waitingTime;
    }
//    public static void setIdGenerator(int idGenerator) {
//        ID_GENERATOR = idGenerator;
//    }

    public String stringInfo() {
        return _name+" "+id+" "+_address+" "+_openingHour+" "+_closingHour+" "+_rate+" "+_waitingTime;
    }
}
