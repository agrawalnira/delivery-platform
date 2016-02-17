package com.social.model;


import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by niranjan.agrawal on 2/8/16.
 */

@XmlRootElement
public class Profile {

    private long id;
    private String username;
    private String password;
    private String name;
    private int role;
    private long ngoId;
    private String phoneNumber;
    private String address;
    private String emailId;

    public Profile(){

    }

    public Profile(String username, String password, String name, int role, String phoneNumber, String address, String emailId, long ngoId) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.emailId = emailId;
        this.ngoId = ngoId;
    }

    public long getNgoId() {
        return ngoId;
    }

    public void setNgoId(long ngoId) {
        this.ngoId = ngoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
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
