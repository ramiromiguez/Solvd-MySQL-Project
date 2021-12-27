package com.spotifi.app.objects;

public class PlaylistContent {
    private int playlistId;
    private int songId;
    public int getPlaylistId() {
        return playlistId;
    }
    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }
    public int getSongId() {
        return songId;
    }
    public void setSongId(int songId) {
        this.songId = songId;
    }
    @Override
    public String toString() {
	return "PlaylistContent [playlistId=" + playlistId + ", songId=" + songId + "]";
    }
    
    
}
