package com.spotifi.app.model.entities;

public class PlaylistFollowed {
    private User user;
    private Playlist playlist;

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public Playlist getPlaylist() {
	return playlist;
    }

    public void setPlaylist(Playlist playlist) {
	this.playlist = playlist;
    }

    @Override
    public String toString() {
	return "PlaylistFollowed [user=" + user + ", playlist=" + playlist + "]";
    }

}
