package com.example.walkinclinic;

public class ServicesClinic {
    private String id;
    private String clinicId;
    private Service service;
    private int rate;

    public ServicesClinic(){
    }

    public ServicesClinic(String clinic, Service service){
        this.clinicId = clinic;
        this.service = service;

    }
    public ServicesClinic(String id, String clinic, Service service){
        this.id = id;
        this.clinicId = clinic;
        this.service = service;
    }
    public ServicesClinic(String id, String clinic, Service service, int rate){
        this.id = id;
        this.clinicId = clinic;
        this.service = service;
        this.rate = rate;
    }

    // getters
    public String getId() {
        return id;
    }
    public Service getService() {
        return service;
    }
    public String getClinicId() {
        return clinicId;
    }
    public int getRate() {
        return rate;
    }

    // setters
    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }
    public void setService(Service service) {
        this.service = service;
    }
    public void setRate(int rate) {
        this.rate = rate;
    }

    public String stringInfo() {
        return id+" "+clinicId+" "+service;
    }
}
