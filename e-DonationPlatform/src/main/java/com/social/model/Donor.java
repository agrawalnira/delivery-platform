package com.social.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by niranjan.agrawal on 2/17/16.
 */

@XmlRootElement
public class Donor {

    private String name;
    private String phoneNumber;
    private String address;
    private String emailId;

    public Donor() {

    }

    public Donor(String name, String phoneNumber, String address, String emailId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
