package com.spotifi.app.controller.mybatisservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.spotifi.app.controller.mybatisservice.builder.ConnectionBuilder;
import com.spotifi.app.model.entities.Artist;
import com.spotifi.app.model.entities.ArtistAsDB;
import com.spotifi.app.model.entities.Country;
import com.spotifi.app.model.entities.Genre;
import com.spotifi.app.model.entities.Song;
import com.spotifi.app.model.entities.SongAsDB;
import com.spotifi.app.model.mybatismapper.SongMapper;

public class SongServiceMyBatis implements SongMapper {

    public Song getObject(int id) {
	Song song = new Song();
	SongAsDB songAsDB = new SongAsDB();
	songAsDB = getObjectAsDB(id);
	song = transformDBObjectToJavaObject(songAsDB);
	return song;
    }

    @Override
    public SongAsDB getObjectAsDB(int id) {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    SongAsDB songAsDB;
	    songAsDB = session.getMapper(SongMapper.class).getObjectAsDB(id);
	    return songAsDB;
	}
    }

    private Song transformDBObjectToJavaObject(SongAsDB songAsDB) {
	GenreServiceMyBatis genreService = new GenreServiceMyBatis();
	CountryServiceMyBatis countryService = new CountryServiceMyBatis();
	ArtistServiceMyBatis artistService = new ArtistServiceMyBatis();
	Genre genre = new Genre();
	Country country = new Country();
	Song song = new Song();
	ArtistAsDB artistAsDB = new ArtistAsDB();
	Artist artist = new Artist();
	genre = genreService.getObject(songAsDB.getGenreId());
	artistAsDB = artistService.getObjectAsDB(songAsDB.getArtistId());
	country = countryService.getObject(artistAsDB.getCountryId());
	artist.setCountry(country);
	artist.setGenre(genre);
	artist.setId(artistAsDB.getId());
	artist.setName(artistAsDB.getName());
	song.setId(songAsDB.getId());
	song.setGenre(genre);
	song.setDuration(songAsDB.getDuration());
	song.setName(songAsDB.getName());
	song.setArtist(artist);
	return song;
    }

    public List<Song> getObjectList() {
	List<SongAsDB> listSongAsDB = new ArrayList<>();
	List<Song> listSong = new ArrayList<>();
	listSongAsDB = getObjectAsDBList();
	for (SongAsDB songAsDB : listSongAsDB) {
	    Song song = new Song();
	    song = transformDBObjectToJavaObject(songAsDB);
	    listSong.add(song);
	}
	return listSong;
    }

    public List<Song> getObjectListWithArtistId(int id) {
	List<SongAsDB> listSongAsDB = new ArrayList<>();
	List<Song> listSong = new ArrayList<>();
	listSongAsDB = getObjectAsDBListWithArtistId(id);
	for (SongAsDB songAsDB : listSongAsDB) {
	    Song song = new Song();
	    song = transformDBObjectToJavaObject(songAsDB);
	    listSong.add(song);
	}
	return listSong;
    }

    @Override
    public List<SongAsDB> getObjectAsDBList() {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    List<SongAsDB> list = new ArrayList<>();
	    list = session.getMapper(SongMapper.class).getObjectAsDBList();
	    return list;
	}
    }

    @Override
    public List<SongAsDB> getObjectAsDBListWithArtistId(int id) {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    List<SongAsDB> list = new ArrayList<>();
	    list = session.getMapper(SongMapper.class).getObjectAsDBListWithArtistId(id);
	    return list;
	}
    }

    @Override
    public void add(SongAsDB song) {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    session.getMapper(SongMapper.class).add(song);
	    session.commit();
	}
    }

    public void add(Song song) {
	SongAsDB songAsDB = new SongAsDB();
	songAsDB.setArtistId(song.getArtist().getId());
	songAsDB.setDuration(song.getDuration());
	songAsDB.setGenreId(song.getGenre().getId());
	songAsDB.setName(song.getName());
	add(songAsDB);
    }

    @Override
    public void update(SongAsDB song) {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    session.getMapper(SongMapper.class).update(song);
	    session.commit();
	}
    }

    public void update(Song song) {

    }

    @Override
    public void delete(int id) {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    session.getMapper(SongMapper.class).delete(id);
	    session.commit();
	}
    }
}
