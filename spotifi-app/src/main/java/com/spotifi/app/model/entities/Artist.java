package com.spotifi.app.model.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Artist {
    private int id;
    private String name;
    private Country country;
    private Genre genre;
    @XmlElementWrapper(name = "albums")
    private List<Album> albumList = new ArrayList<>();
    @XmlElementWrapper(name = "singles")
    private List<Song> singleList = new ArrayList<>();

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
    public Country getCountry() {
	return country;
    }

    public void setCountry(Country country) {
	this.country = country;
    }

    @XmlElement
    public Genre getGenre() {
	return genre;
    }

    public void setGenre(Genre genre) {
	this.genre = genre;
    }

    @XmlTransient
    public List<Album> getAlbumList() {
	return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
	this.albumList = albumList;
    }

    @XmlTransient
    public List<Song> getSingleList() {
	return singleList;
    }

    public void setSingleList(List<Song> singleList) {
	this.singleList = singleList;
    }

    @Override
    public String toString() {
	return "Artist [id=" + id + ", name=" + name + ", country=" + country + ", genre=" + genre + ", albumList="
		+ albumList + ", singleList=" + singleList + "]";
    }

}
