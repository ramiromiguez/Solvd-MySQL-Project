package com.spotifi.app.view;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.spotifi.app.controller.mybatisservice.ArtistServiceMyBatis;
import com.spotifi.app.controller.mybatisservice.GenreServiceMyBatis;
import com.spotifi.app.controller.mybatisservice.SongServiceMyBatis;
import com.spotifi.app.model.dao.AlbumDAO;
import com.spotifi.app.model.entities.Artist;
import com.spotifi.app.model.entities.ArtistAsDB;
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
	Genre genre = new Genre();
	Artist artist = new Artist();

	List<Genre> genreList = new ArrayList<>();
	List<ArtistAsDB> artistList = new ArrayList<>();
	List<Song> songList = new ArrayList<>();
	List<SongAsDB> songListDB = new ArrayList<>();
	Song song = new Song();

	genreList = genreService.getObjectList();

	songList = songService.getObjectList();
	songListDB = songService.getObjectAsDBList();
	song = songService.getObject(10);
	LOG.log(Level.INFO, song.toString());
	printList(songListDB);
    }
}
