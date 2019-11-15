package com.example.walkinclinic;

public class ServicesClinic {
    private String id;
    private String clinicId;
    private Service service;
    //private String _name;
    //private int _appropriateStaff; // 0 = doctor, 1 = nurse, 2 = staff

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

    // setters
    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }
    public void setService(Service service) {
        this.service = service;
    }

    public String stringInfo() {
        return id+" "+clinicId+" "+service;
    }
}
