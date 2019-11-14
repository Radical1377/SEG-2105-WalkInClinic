package com.example.walkinclinic;

public class Employee extends User {

    private String address = null;
    private String phoneNumber = null;
    private String clinic = null;
    private String insurance = null;
    private String paymentMethod = null;
    private boolean completed;

    //class constructor
    public Employee(String username, String password, String first_name, String last_name, String email, boolean needsEncrypt){
        super(username, password, first_name, last_name, email,1, needsEncrypt);
        this.completed = false;
    }

    public Employee(String username, String password, String first_name, String last_name, String email, boolean needsEncrypt, String address, String phoneNumber, String insurance, String clinic, String payment){
        super(username, password, first_name, last_name, email,1, needsEncrypt);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.clinic = clinic;
        this.insurance = insurance;
        this.paymentMethod = payment;
        this.completed = true;
    }

    public String getAddress() {
        return address;
    }
    public String getClinic() {
        return clinic;
    }
    public String getInsurance() {
        return insurance;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setClinic(String clinic) {
        this.clinic = clinic;
    }
    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
