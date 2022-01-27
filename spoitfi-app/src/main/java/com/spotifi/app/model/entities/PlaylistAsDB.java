package com.spotifi.app.model.entities;

import java.sql.Date;

public class PlaylistAsDB {
    private int id;
    private String name;
    private Date date;
    private int UserId;

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

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    public int getUserId() {
	return UserId;
    }

    public void setUserId(int userId) {
	UserId = userId;
    }

}
