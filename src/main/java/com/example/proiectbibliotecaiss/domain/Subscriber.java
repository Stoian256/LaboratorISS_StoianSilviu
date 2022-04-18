package com.example.proiectbibliotecaiss.domain;

public class Subscriber {
    private int subscriberID;
    private String CNP;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String password;

    public Subscriber(int subscriberID, String CNP, String firstName, String lastName, String address, String phone, String password) {
        this.subscriberID = subscriberID;
        this.CNP = CNP;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.password = password;
    }

    public Subscriber(String CNP, String firstName, String lastName, String address, String phone, String password) {
        this.CNP = CNP;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.password = password;
    }

    public int getSubscriberID() {
        return subscriberID;
    }

    public void setSubscriberID(int subscriberID) {
        this.subscriberID = subscriberID;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhoneName(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "subscriberID=" + subscriberID +
                ", CNP='" + CNP + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", addressName='" + address + '\'' +
                ", phoneName='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
