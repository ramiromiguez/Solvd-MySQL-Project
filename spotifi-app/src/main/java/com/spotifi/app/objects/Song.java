package com.spotifi.app.objects;

public class Song {
    private int id;
    private String name;
    private float duration;
    private int genreId;
    private int artistId;
    
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
    public float getDuration() {
        return duration;
    }
    public void setDuration(float duration) {
        this.duration = duration;
    }
    public int getGenreId() {
        return genreId;
    }
    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }
    public int getArtistId() {
        return artistId;
    }
    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }
   
    @Override
    public String toString() {
	return "Song [id=" + id + ", name=" + name + ", duration=" + duration + ", genreId=" + genreId + ", artistId="
		+ artistId + "]";
    }
    
    
}
