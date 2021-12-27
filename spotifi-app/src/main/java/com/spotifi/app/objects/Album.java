package com.spotifi.app.objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Album {
    private int id;
    private String name;
    private int artistId;
    private int genreId;
    private int length;

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

    public int getLength() {
	return length;
    }

    public void setLength(int length) {
	this.length = length;
    }

    @Override
    public String toString() {
	return "Album [id=" + id + ", name=" + name + ", artistId=" + artistId + ", genreId=" + genreId + ", length="
		+ length + "]";
    }

}
