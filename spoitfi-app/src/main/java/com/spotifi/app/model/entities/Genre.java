package com.spotifi.app.model.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@JsonRootName(value = "genre")
@XmlRootElement(name = "genre")
public class Genre {
    private int id;
    private String name;

    @XmlAttribute
    @JsonGetter("id")
    public int getId() {
	return id;
    }

    @JsonSetter
    public void setId(int id) {
	this.id = id;
    }

    @XmlElement
    @JsonGetter("name")
    public String getName() {
	return name;
    }

    @JsonSetter
    public void setName(String name) {
	this.name = name;
    }

    @Override
    public String toString() {
	return "Genre [id=" + id + ", name=" + name + "]";
    }

}
