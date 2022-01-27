package com.spotifi.app.model.entities;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Country {
    private int id;
    private String name;

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

    @Override
    public String toString() {
	return "Country [id=" + id + ", name=" + name + "]";
    }
}
