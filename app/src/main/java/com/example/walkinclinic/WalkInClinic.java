package com.example.walkinclinic;

public class WalkInClinic {
    private String _name;
    private String _id;
    private String _address;
    private int _openingHour;
    private int _closingHour;
    private float _rate;
    private int _waitingTime;
    //private int[] typeServices; // ??????

    //private static int ID_GENERATOR=1;

    public WalkInClinic(){
    }

    public WalkInClinic (String name, String address, int oh, int ch){
        _name = name;
//        _id = String.valueOf(ID_GENERATOR);
        _address = address;
        _openingHour = oh;
        _closingHour = ch;
        _rate = 0;
        _waitingTime = 0;

        //ID_GENERATOR++;
    }
    public WalkInClinic (String id, String name, String address, int oh, int ch){
        _name = name;
        _id = id;
        _address = address;
        _openingHour = oh;
        _closingHour = ch;
//        _rate = 0;
//        _waitingTime = 0;
    }

    public WalkInClinic (String id, String name, String address, int oh, int ch, int rate, int wait){
        _name = name;
        _id = id;
        _address = address;
        _openingHour = oh;
        _closingHour = ch;
        _rate = rate;
        _waitingTime = wait;
    }


    // getters
    public String getId(){
        return _id;
    }
    public String getName() {
        return _name;
    }
    public float getRate() {
        return _rate;
    }
    public int getClosingHour() {
        return _closingHour;
    }
    public int getOpeningHour() {
        return _openingHour;
    }
    public int getWaitingTime() {
        return _waitingTime;
    }
    public String getAddress() {
        return _address;
    }
//   // public static int getIdGenerator() {
//        return ID_GENERATOR;
//    }

    // setters
    public void setName(String name) {
        _name = name;
    }
    public void setAddress(String address) {
        _address = address;
    }
    public void setOpeningHour(int openingHour) {
        _openingHour = openingHour;
    }
    public void setClosingHour(int closingHour) {
        _closingHour = closingHour;
    }
    public void setRate(float rate) {
        _rate = rate;
    }
    public void setWaitingTime(int waitingTime) {
        _waitingTime = waitingTime;
    }
//    public static void setIdGenerator(int idGenerator) {
//        ID_GENERATOR = idGenerator;
//    }
}
