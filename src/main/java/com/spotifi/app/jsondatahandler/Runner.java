package com.spotifi.app.jsondatahandler;

import java.util.ArrayList;
import java.util.List;

import com.spotifi.app.objects.Album;
import com.spotifi.app.objects.Artist;
import com.spotifi.app.objects.Country;
import com.spotifi.app.objects.Genre;
import com.spotifi.app.objects.Song;

public class Runner {
    public static void main(String[] args) {

	JsonCreator jsonCreator = new JsonCreator();
	Song song = new Song();
	Genre genre = new Genre();
	Artist artist = new Artist();
	Country country = new Country();
	Album album = new Album();
	List<Song> songList = new ArrayList<>();

	country.setId(1);
	country.setName("United States");
	genre.setId(1);
	genre.setName("Trap");
	artist.setId(1);
	artist.setName("Suicide Boys");
	artist.setCountry(country);
	artist.setGenre(genre);
	song.setId(1);
	song.setDuration(2.1f);
	song.setGenre(genre);
	song.setName("Oracle");
	song.setArtist(artist);
	album.setArtist(artist);
	album.setGenre(genre);
	album.setId(1);
	album.setLength(1);
	album.setName("Roses");
	songList.add(song);
	album.setAlbumSongs(songList);

	jsonCreator.Creator(album, "src/main/resources/albumJackson.json");

    }
}
