package com.spotifi.app.model.xmlmapper;

import java.io.File;

import com.spotifi.app.model.entities.Genre;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class GenreJaxb {
    public static void main(String[] args) throws JAXBException {

	JAXBContext context;

	context = JAXBContext.newInstance(Genre.class);

	Genre genre = new Genre();
	genre.setId(1);
	genre.setName("Trap");
	Marshaller marshaller = context.createMarshaller();
	marshaller.marshal(genre, new File("src/main/resources/genreJaxb.xml"));
	Unmarshaller unmarshaller = context.createUnmarshaller();
	Object unmarshalled = unmarshaller.unmarshal(new File("src/main/resources/genreJaxb.xml"));
	System.out.println(unmarshalled);

    }
}
