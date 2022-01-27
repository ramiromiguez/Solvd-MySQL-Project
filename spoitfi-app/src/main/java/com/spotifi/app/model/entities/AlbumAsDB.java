package com.spotifi.app.model.entities;

public class AlbumAsDB {
    private int id;
    private String name;
    private int length;
    private int artistId;
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

    public int getLength() {
	return length;
    }

    public void setLength(int length) {
	this.length = length;
    }

    public int getArtistId() {
	return artistId;
    }

    public void setArtistId(int artistId) {
	this.artistId = artistId;
    }

    public int getGenreId() {
	return genreId;
    }

    public void setGenreId(int genreId) {
	this.genreId = genreId;
    }

    @Override
    public String toString() {
	return "AlbumAsDB [id=" + id + ", name=" + name + ", length=" + length + ", artistId=" + artistId + ", genreId="
		+ genreId + "]";
    }

}
