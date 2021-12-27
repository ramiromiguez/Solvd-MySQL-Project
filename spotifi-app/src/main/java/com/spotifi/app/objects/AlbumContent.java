package com.spotifi.app.objects;

public class AlbumContent {
    private int songId;
    private int albumId;
    public int getSongId() {
        return songId;
    }
    public void setSongId(int songId) {
        this.songId = songId;
    }
    public int getAlbumId() {
        return albumId;
    }
    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }
    @Override
    public String toString() {
	return "AlbumContent [songId=" + songId + ", albumId=" + albumId + "]";
    }
    
    
    
}
