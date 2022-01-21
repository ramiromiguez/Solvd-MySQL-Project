package com.spotifi.app.model.entities;

public class AlbumAsDB {
    private int albumId;
    private String albumName;
    private int artistId;
    private int genreId;
    private int albumLength;

    public int getAlbumId() {
	return albumId;
    }

    public void setAlbumId(int albumId) {
	this.albumId = albumId;
    }

    public String getAlbumName() {
	return albumName;
    }

    public void setAlbumName(String albumName) {
	this.albumName = albumName;
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

    public int getAlbumLength() {
	return albumLength;
    }

    public void setAlbumLength(int albumLength) {
	this.albumLength = albumLength;
    }

}
