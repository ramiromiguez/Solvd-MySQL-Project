package com.spotifi.app.model.entities;

import java.util.Date;

public class SongLiked {
    private Date date;
    private Song song;
    private User user;

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    public Song getSong() {
	return song;
    }

    public void setSong(Song song) {
	this.song = song;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    @Override
    public String toString() {
	return "SongLiked [date=" + date + ", song=" + song + ", user=" + user + "]";
    }

}
