package com.spotifi.app.model.entities;

import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
    private int id;
    private String name;
    private LocalDate birth;

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
    public LocalDate getBirth() {
	return birth;
    }

    public void setBirth(LocalDate birth) {
	this.birth = birth;
    }

    @Override
    public String toString() {
	return "User [id=" + id + ", name=" + name + ", birth=" + birth + "]";
    }

}
