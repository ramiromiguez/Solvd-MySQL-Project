package com.spotifi.app.view;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.spotifi.app.controller.mybatisservice.AlbumServiceMyBatis;
import com.spotifi.app.controller.mybatisservice.ArtistServiceMyBatis;
import com.spotifi.app.controller.mybatisservice.CountryServiceMyBatis;
import com.spotifi.app.controller.mybatisservice.GenreServiceMyBatis;
import com.spotifi.app.controller.mybatisservice.SongServiceMyBatis;
import com.spotifi.app.model.dao.AlbumDAO;
import com.spotifi.app.model.entities.Album;
import com.spotifi.app.model.entities.AlbumAsDB;
import com.spotifi.app.model.entities.Artist;
import com.spotifi.app.model.entities.ArtistAsDB;
import com.spotifi.app.model.entities.Country;
import com.spotifi.app.model.entities.Genre;
import com.spotifi.app.model.entities.Song;
import com.spotifi.app.model.entities.SongAsDB;

public class MyBatisRunner {

    private static final Logger LOG = Logger.getLogger(AlbumDAO.class.getName());

    private static <T> void printList(List<T> list) {
	list.forEach(x -> LOG.log(Level.INFO, x.toString()));
    }

    public static void main(String[] args) {
	GenreServiceMyBatis genreService = new GenreServiceMyBatis();
	ArtistServiceMyBatis artistService = new ArtistServiceMyBatis();
	SongServiceMyBatis songService = new SongServiceMyBatis();
	CountryServiceMyBatis countryService = new CountryServiceMyBatis();
	AlbumServiceMyBatis albumService = new AlbumServiceMyBatis();
	Genre genre = new Genre();

	List<Genre> genreList = new ArrayList<>();
	List<Country> countryList = new ArrayList<>();
	List<Artist> artistList = new ArrayList<>();
	List<ArtistAsDB> artistListDB = new ArrayList<>();
	List<Song> songList = new ArrayList<>();
	List<SongAsDB> songListDB = new ArrayList<>();
	List<Album> albumList = new ArrayList<>();
	List<AlbumAsDB> albumListDB = new ArrayList<>();

	Song song = new Song();
	SongAsDB songAsDB = new SongAsDB();
	Artist artist = new Artist();

	songAsDB.setArtistId(1);
	songAsDB.setDuration(2);
	songAsDB.setGenreId(3);
	songAsDB.setId(21);
	songAsDB.setName("xddddd");

	songListDB = songService.getObjectAsDBList();
	artistListDB = artistService.getObjectAsDBList();
	countryList = countryService.getObjectList();

	artistList = artistService.getObjectList();
	songList = songService.getObjectList();
	albumListDB = albumService.getObjectAsDBList();
	albumList = albumService.getObjectList();
	printList(albumList);
	LOG.log(Level.WARNING, artist.toString());
    }
}
