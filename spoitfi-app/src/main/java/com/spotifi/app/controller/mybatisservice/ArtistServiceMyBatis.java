package com.spotifi.app.controller.mybatisservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.spotifi.app.controller.mybatisservice.builder.ConnectionBuilder;
import com.spotifi.app.model.entities.Artist;
import com.spotifi.app.model.entities.ArtistAsDB;
import com.spotifi.app.model.entities.Song;
import com.spotifi.app.model.mybatismapper.ArtistMapper;

public class ArtistServiceMyBatis implements ArtistMapper {

    private Artist transformDBObjectToJavaObject(ArtistAsDB artistAsDB) {
	GenreServiceMyBatis genreService = new GenreServiceMyBatis();
	CountryServiceMyBatis countryService = new CountryServiceMyBatis();
	SongServiceMyBatis songService = new SongServiceMyBatis();
	List<Song> songList = new ArrayList<>();
	Artist artist = new Artist();

	songList = songService.getObjectListWithArtistId(artistAsDB.getId());
	artist.setId(artistAsDB.getId());
	artist.setName(artistAsDB.getName());
	artist.setCountry(countryService.getObject(artistAsDB.getCountryId()));
	artist.setGenre(genreService.getObject(artistAsDB.getGenreId()));
	artist.setSingleList(songList);
	return artist;
    }

    private ArtistAsDB transformJavaObjectToDBOjbect(Artist artist) {
	ArtistAsDB artistAsDB = new ArtistAsDB();
	artistAsDB.setCountryId(artist.getId());
	artistAsDB.setGenreId(artist.getCountry().getId());
	artistAsDB.setId(artist.getId());
	artistAsDB.setName(artist.getName());
	return artistAsDB;
    }

    @Override
    public ArtistAsDB getObjectAsDB(int id) {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    ArtistAsDB artistAsDB;
	    artistAsDB = session.getMapper(ArtistMapper.class).getObjectAsDB(id);
	    return artistAsDB;
	}
    }

    public Artist getObject(int id) {
	Artist artist = new Artist();
	ArtistAsDB artistAsDB = new ArtistAsDB();
	artistAsDB = getObjectAsDB(id);
	artist = transformDBObjectToJavaObject(artistAsDB);
	return artist;
    }

    public Artist getSmallArtist(int id) {
	ArtistAsDB artistAsDB = new ArtistAsDB();
	Artist artist = new Artist();
	GenreServiceMyBatis genreService = new GenreServiceMyBatis();
	CountryServiceMyBatis countryService = new CountryServiceMyBatis();
	artistAsDB = getObjectAsDB(id);
	artist.setCountry(countryService.getObject(artistAsDB.getCountryId()));
	artist.setGenre(genreService.getObject(artistAsDB.getGenreId()));
	artist.setId(id);
	artist.setName(artistAsDB.getName());

	return artist;
    }

    @Override
    public List<ArtistAsDB> getObjectAsDBList() {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    List<ArtistAsDB> list = new ArrayList<>();
	    list = session.getMapper(ArtistMapper.class).getObjectAsDBList();
	    return list;
	}
    }

    public List<Artist> getObjectList() {
	List<Artist> artistList = new ArrayList<>();
	List<ArtistAsDB> artistAsDBList = getObjectAsDBList();
	for (ArtistAsDB artistAsDB : artistAsDBList) {
	    Artist artist = new Artist();
	    artist = transformDBObjectToJavaObject(artistAsDB);
	    artistList.add(artist);
	}
	return artistList;
    }

    @Override
    public void add(ArtistAsDB artist) {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    session.getMapper(ArtistMapper.class).add(artist);
	    session.commit();
	}
    }

    public void add(Artist artist) {
	ArtistAsDB artistAsDB = new ArtistAsDB();
	artistAsDB = transformJavaObjectToDBOjbect(artist);
	add(artistAsDB);
    }

    @Override
    public void update(ArtistAsDB artist) {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    session.getMapper(ArtistMapper.class).update(artist);
	    session.commit();
	}
    }

    public void update(Artist artist) {
	ArtistAsDB artistAsDB = new ArtistAsDB();
	artistAsDB = transformJavaObjectToDBOjbect(artist);
	update(artistAsDB);
    }

    @Override
    public void delete(int id) {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    session.getMapper(ArtistMapper.class).delete(id);
	    session.commit();
	}
    }
}