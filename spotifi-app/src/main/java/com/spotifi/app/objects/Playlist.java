package com.spotifi.app.objects;

import java.util.Date;

public class Playlist {
    private int id;
    private String name;
    private Date creationDate;
    private int userId;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    @Override
    public String toString() {
	return "Playlist [id=" + id + ", name=" + name + ", creationDate=" + creationDate + ", userId=" + userId + "]";
    }
    
    
    
}
