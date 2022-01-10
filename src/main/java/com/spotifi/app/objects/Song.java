package com.spotifi.app.objects;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Song {
    private int id;
    private String name;
    private float duration;
    private Genre genre;
    private Artist artist;

    @XmlAttribute
    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    @XmlElement
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @XmlElement
    public float getDuration() {
	return duration;
    }

    public void setDuration(float duration) {
	this.duration = duration;
    }

    @XmlElement
    public Genre getGenre() {
	return genre;
    }

    public void setGenre(Genre genre) {
	this.genre = genre;
    }

    @XmlTransient
    public int getArtistId() {
	return artist.getId();
    }

    @XmlElement
    public Artist getArtist() {
	return artist;
    }

    public void setArtist(Artist artist) {
	this.artist = artist;
    }

    @Override
    public String toString() {
	return "Song [id=" + id + ", name=" + name + ", duration=" + duration + ", genre=" + genre + ", artist="
		+ artist + "]";
    }

}
