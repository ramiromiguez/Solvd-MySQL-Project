package com.spotifi.app.controller.jbdcservice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.spotifi.app.controller.jbdcservice.connection.DBConnection;
import com.spotifi.app.model.dao.SongDAO;
import com.spotifi.app.model.dao.interfaces.IDAO;
import com.spotifi.app.model.entities.Song;
import com.spotifi.app.model.entities.SongAsDB;

public class SongService implements IDAO<Song> {
    private SongDAO songDao = new SongDAO(new DBConnection());

    private SongAsDB objectToDataBaseObject(Song song) {
	SongAsDB songAsDB = new SongAsDB();
	songAsDB.setId(song.getId());
	songAsDB.setName(song.getName());
	songAsDB.setDuration(song.getDuration());
	songAsDB.setGenreId(song.getGenre().getId());

	return songAsDB;
    }

    @Override
    public void add(Song song) throws SQLException {
	SongAsDB songAsDB = new SongAsDB();
	objectToDataBaseObject(song);
	songDao.add(songAsDB);
    }

    @Override
    public void update(Song song) throws SQLException {
	SongAsDB songAsDB = new SongAsDB();
	objectToDataBaseObject(song);
	songDao.update(songAsDB);
    }

    @Override
    public void delete(int id) throws SQLException {
	songDao.delete(id);
    }

    @Override
    public Song getObject(int id) throws SQLException {
	SongAsDB songAsDB = new SongAsDB();
	Song song = new Song();
	ArtistService artistService = new ArtistService();
	GenreService genreService = new GenreService();

	songAsDB = songDao.getObject(id);
	song.setArtist(artistService.getSmallArtist(songAsDB.getArtistId()));
	song.setDuration(songAsDB.getDuration());
	song.setGenre(genreService.getObject(songAsDB.getGenreId()));
	song.setId(songAsDB.getId());
	song.setName(songAsDB.getName());

	return song;
    }

    public Song getObjectWOArtist(int id) throws SQLException {
	SongAsDB songAsDB = new SongAsDB();
	Song song = new Song();
	GenreService genreService = new GenreService();

	songAsDB = songDao.getObject(id);
	song.setDuration(songAsDB.getDuration());
	song.setGenre(genreService.getObject(songAsDB.getGenreId()));
	song.setId(songAsDB.getId());
	song.setName(songAsDB.getName());

	return song;
    }

    @Override
    public List<Song> getObjectList() throws SQLException {
	List<SongAsDB> songAsDBList = new ArrayList<>();
	List<Song> songList = new ArrayList<>();
	ArtistService artistService = new ArtistService();
	GenreService genreService = new GenreService();

	songAsDBList = songDao.getObjectList();
	for (SongAsDB songAsDB : songAsDBList) {
	    Song song = new Song();
	    song.setArtist(artistService.getSmallArtist(songAsDB.getArtistId()));
	    song.setDuration(songAsDB.getDuration());
	    song.setGenre(genreService.getObject(songAsDB.getGenreId()));
	    song.setId(songAsDB.getId());
	    song.setName(songAsDB.getName());
	    songList.add(song);
	}

	return songList;
    }
}
