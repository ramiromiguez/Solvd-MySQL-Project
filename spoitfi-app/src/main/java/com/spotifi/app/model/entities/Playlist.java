package com.spotifi.app.model.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Playlist {
    private int id;
    private String name;
    private Date creationDate;
    private User user;
    private List<Song> playlistSongs = new ArrayList<>();

    public List<Song> getPlaylistSongs() {
	return playlistSongs;
    }

    public void setPlaylistSongs(List<Song> playlistSongs) {
	this.playlistSongs = playlistSongs;
    }

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

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    @Override
    public String toString() {
	return "Playlist [id=" + id + ", name=" + name + ", creationDate=" + creationDate + ", user=" + user
		+ ", playlistSongs=" + playlistSongs + "]";
    }

}
