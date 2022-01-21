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
import com.spotifi.app.model.mybatismapper.ArtistMapper;

public class ArtistServiceMyBatis implements ArtistMapper {

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

    private Artist transformDBObjectToJavaObject(ArtistAsDB artistAsDB) {
	GenreServiceMyBatis genreService = new GenreServiceMyBatis();
	CountryServiceMyBatis countryService = new CountryServiceMyBatis();
	ArtistServiceMyBatis artistService = new ArtistServiceMyBatis();
	SongServiceMyBatis songService = new SongServiceMyBatis();
	Genre genre = new Genre();
	Country country = new Country();
	List<Song> songList = new ArrayList<>();
	Artist artist = new Artist();
	songList = songService.getObjectListWithArtistId(artistAsDB.getId());
	artist.setId(artistAsDB.getId());
	artist.setName(artistAsDB.getName());
	artist.setCountry(country);
	artist.setGenre(genre);
	artist.setSingleList(songList);
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
    }

    @Override
    public void add(ArtistAsDB artist) {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    session.getMapper(ArtistMapper.class).add(artist);
	    session.commit();
	}
    }

    @Override
    public void update(ArtistAsDB artist) {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    session.getMapper(ArtistMapper.class).update(artist);
	    session.commit();
	}
    }

    @Override
    public void delete(int id) {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    session.getMapper(ArtistMapper.class).delete(id);
	    session.commit();
	}
    }
}
