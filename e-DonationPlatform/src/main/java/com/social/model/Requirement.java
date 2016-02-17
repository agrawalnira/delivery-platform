package com.social.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by niranjan.agrawal on 2/11/16.
 */

public class Requirement {

    private long id;
    private String title;
    private String description;
    private String status;
    private Date createdDate;
    private Date lastUpdated;


    public Requirement() {

    }

    public Requirement(long id,String title, String description, String status, Date createdDate, Date lastUpdated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdDate = createdDate;
        this.lastUpdated = lastUpdated;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}