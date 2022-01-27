package com.spotifi.app.controller.mybatisservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.spotifi.app.controller.mybatisservice.builder.ConnectionBuilder;
import com.spotifi.app.model.entities.Artist;
import com.spotifi.app.model.entities.Song;
import com.spotifi.app.model.entities.SongAsDB;
import com.spotifi.app.model.mybatismapper.SongMapper;

public class SongServiceMyBatis implements SongMapper {

    public Song transformDBObjectToJavaObject(SongAsDB songAsDB) {
	GenreServiceMyBatis genreService = new GenreServiceMyBatis();
	ArtistServiceMyBatis artistService = new ArtistServiceMyBatis();
	Artist artist = new Artist();
	Song song = new Song();
	artist = artistService.getSmallArtist(songAsDB.getArtistId());

	song.setId(songAsDB.getId());
	song.setGenre(genreService.getObject(songAsDB.getGenreId()));
	song.setDuration(songAsDB.getDuration());
	song.setName(songAsDB.getName());
	song.setArtist(artist);
	return song;
    }

    private SongAsDB transformObjectToDBObject(Song song) {
	SongAsDB songAsDB = new SongAsDB();
	songAsDB.setArtistId(song.getArtistId());
	songAsDB.setDuration(song.getDuration());
	songAsDB.setGenreId(song.getGenre().getId());
	songAsDB.setId(song.getId());
	songAsDB.setName(song.getName());
	return songAsDB;
    }

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
	add(transformObjectToDBObject(song));
    }

    @Override
    public void update(SongAsDB song) {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    session.getMapper(SongMapper.class).update(song);
	    session.commit();
	}
    }

    public void update(Song song) {
	update(transformObjectToDBObject(song));
    }

    @Override
    public void delete(int id) {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    session.getMapper(SongMapper.class).delete(id);
	    session.commit();
	}
    }
}
