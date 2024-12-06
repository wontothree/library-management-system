package com.cruds.model;

public class Member {
    private String memberId;
    private String fullName;
    private String phoneNumber;
    private String streetAddress;
    private String emailAddress;

    // constructor
    public Member(String memberId, String fullName, String phoneNumber, String streetAddress, String emailAddress) {
        this.memberId = memberId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.emailAddress = emailAddress;
    }

    // getter and setter for memberId
    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    // getter and setter for fullName
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // getter and setter for phoneNumber
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // getter and setter for streetAddress
    public String getStreetAddress() {
        return streetAddress;
    }
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    // getter and setter for emailAddress
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    // Override toString method to display Member object information
    @Override
    public String toString() {
        return "Member [memberId=" + memberId + ", fullName=" + fullName + ", phoneNumber=" + phoneNumber
                + ", streetAddress=" + streetAddress + ", emailAddress=" + emailAddress + "]";
    }
}
