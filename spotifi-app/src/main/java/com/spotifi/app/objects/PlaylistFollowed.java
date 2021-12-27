package com.spotifi.app.objects;

public class PlaylistFollowed {
    private int userId;
    private int playlistId;
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getPlaylistId() {
        return playlistId;
    }
    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }
    @Override
    public String toString() {
	return "PlaylistFollowed [userId=" + userId + ", playlistId=" + playlistId + "]";
    }
    
    
}
