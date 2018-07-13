package com.example.sheetal.hp;

public class motherDetails {
    private String motherName;
    private String motherAge;
    private String motherFatherName;
    private String expectingDate;
    private String Address;
    private String desp;
    private String userId;
    public motherDetails() {
    }

    public motherDetails(String motherName, String motherAge, String motherFatherName, String expectingDate, String address, String desp, String userId) {
        this.motherName = motherName;
        this.motherAge = motherAge;
        this.motherFatherName = motherFatherName;
        this.expectingDate = expectingDate;
        Address = address;
        this.desp = desp;
        this.userId = userId;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherAge() {
        return motherAge;
    }

    public void setMotherAge(String motherAge) {
        this.motherAge = motherAge;
    }

    public String getMotherFatherName() {
        return motherFatherName;
    }

    public void setMotherFatherName(String motherFatherName) {
        this.motherFatherName = motherFatherName;
    }

    public String getExpectingDate() {
        return expectingDate;
    }

    public void setExpectingDate(String expectingDate) {
        this.expectingDate = expectingDate;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
