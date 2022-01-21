package com.spotifi.app.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlTransient;

@JsonRootName(value = "album")
@XmlRootElement
public class Album {
    private int id;
    private String name;
    private Artist artist;
    private Genre genre;
    @XmlElementWrapper(name = "songs")
    private List<Song> albumSongs = new ArrayList<>();
    private int length;

    @JsonGetter
    @XmlAttribute
    public int getId() {
	return id;
    }

    @JsonSetter
    public void setId(int id) {
	this.id = id;
    }

    @JsonGetter
    @XmlElement
    public String getName() {
	return name;
    }

    @JsonSetter
    public void setName(String name) {
	this.name = name;
    }

    @JsonGetter
    @XmlElement
    public Artist getArtist() {
	return artist;
    }

    @JsonSetter
    public void setArtist(Artist artist) {
	this.artist = artist;
    }

    @JsonIgnoreProperties
    @XmlTransient
    public int getArtistId() {
	return artist.getId();
    }

    @JsonGetter
    @XmlElement
    public Genre getGenre() {
	return genre;
    }

    @JsonSetter
    public void setGenre(Genre genre) {
	this.genre = genre;
    }

    @JsonGetter
    @XmlElement
    public int getLength() {
	return length;
    }

    @JsonSetter
    public void setLength(int length) {
	this.length = length;
    }

    @JsonGetter
    @XmlTransient
    public List<Song> getAlbumSongs() {
	return albumSongs;
    }

    public void setAlbumSongs(List<Song> albumSongs) {
	this.albumSongs = albumSongs;
    }

    @Override
    public String toString() {
	return "Album [id=" + id + ", name=" + name + ", artist=" + artist + ", genre=" + genre + ", albumSongs="
		+ albumSongs + ", length=" + length + "]";
    }

}
