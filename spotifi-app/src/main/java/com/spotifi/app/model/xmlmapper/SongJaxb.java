package com.spotifi.app.model.xmlmapper;

import java.io.File;

import com.spotifi.app.model.entities.Artist;
import com.spotifi.app.model.entities.Country;
import com.spotifi.app.model.entities.Genre;
import com.spotifi.app.model.entities.Song;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class SongJaxb {
    public static void main(String[] args) throws JAXBException {

	JAXBContext context;

	context = JAXBContext.newInstance(Song.class);

	Genre genre = new Genre();
	genre.setId(1);
	genre.setName("Trap");
	Country country = new Country();
	country.setId(1);
	country.setName("United States");
	Artist artist = new Artist();
	artist.setId(1);
	artist.setCountry(country);
	artist.setGenre(genre);
	artist.setName("Suicide boys");
	Song song = new Song();
	song.setArtist(artist);
	song.setDuration(2);
	song.setGenre(genre);
	song.setId(1);
	song.setName("Oracle");

	Marshaller marshaller = context.createMarshaller();
	marshaller.marshal(song, new File("src/main/resources/songJaxb.xml"));
	Unmarshaller unmarshaller = context.createUnmarshaller();
	Object unmarshalled = unmarshaller.unmarshal(new File("src/main/resources/songJaxb.xml"));
	System.out.println(unmarshalled);

    }
}
