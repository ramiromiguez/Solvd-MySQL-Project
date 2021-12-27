package com.spotifi.app.objects;

public class ArtistSingles {
    private int artistId;
    private int songId;
   
    public int getArtistId() {
        return artistId;
    }
    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }
    public int getSongId() {
        return songId;
    }
    public void setSongId(int songId) {
        this.songId = songId;
    }
    @Override
    public String toString() {
	return "ArtistSingles [artistId=" + artistId + ", songId=" + songId + "]";
    }
    
}
