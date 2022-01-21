package com.spotifi.app.model.xmlmapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.spotifi.app.model.entities.Album;
import com.spotifi.app.model.entities.Artist;
import com.spotifi.app.model.entities.Country;
import com.spotifi.app.model.entities.Genre;
import com.spotifi.app.model.entities.Song;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class AlbumJaxb {
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
	List<Song> albumSongs = new ArrayList<>();
	Song song = new Song();
	song.setArtist(artist);
	song.setDuration(2);
	song.setGenre(genre);
	song.setId(1);
	song.setName("Eclipse");
	albumSongs.add(song);
	song.setArtist(artist);
	song.setDuration(2.1f);
	song.setGenre(genre);
	song.setId(2);
	song.setName("Uglier");
	albumSongs.add(song);
	Album album = new Album();
	album.setArtist(artist);
	album.setGenre(genre);
	album.setId(1);
	album.setLength(2);
	album.setName("Eternal Grey");
	album.setAlbumSongs(albumSongs);

	Marshaller marshaller = context.createMarshaller();
	marshaller.marshal(album, new File("src/main/resources/albumJaxb.xml"));
	Unmarshaller unmarshaller = context.createUnmarshaller();
	Object unmarshalled = unmarshaller.unmarshal(new File("src/main/resources/albumJaxb.xml"));
	System.out.println(unmarshalled);

    }
}
