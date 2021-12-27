package com.spotifi.app.objects;

public class Artist {
    private int id;
    private String name;
    private int countryId;
    private int genreId;
    
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

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    @Override
    public String toString() {
	return "Artist [id=" + id + ", name=" + name + ", countryId=" + countryId + ", genreId=" + genreId + "]";
    }
 
    
}
