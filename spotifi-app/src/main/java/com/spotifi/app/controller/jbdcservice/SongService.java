package com.spotifi.app.controller.jbdcservice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.spotifi.app.controller.jbdcservice.connection.DBConnection;
import com.spotifi.app.model.dao.ArtistDAO;
import com.spotifi.app.model.dao.SongDAO;
import com.spotifi.app.model.dao.interfaces.IDAO;
import com.spotifi.app.model.entities.Artist;
import com.spotifi.app.model.entities.Song;

public class SongService implements IDAO<Song> {
    private SongDAO songDAO = new SongDAO(new DBConnection());
    private ArtistDAO artistDao = new ArtistDAO(new DBConnection());

    @Override
    public void add(Song song) throws SQLException {
	songDAO.add(song);
    }

    @Override
    public void update(Song song) throws SQLException {
	songDAO.update(song);
    }

    @Override
    public void delete(int id) throws SQLException {
	songDAO.delete(id);
    }

    @Override
    public Song getObject(int id) throws SQLException {
	List<Artist> artistList = new ArrayList<>();
	artistList = artistDao.getObjectList();
	return songDAO.getObject(id, artistList);
    }

    @Override
    public List<Song> getObjectList() throws SQLException {
	List<Artist> artistList = new ArrayList<>();
	artistList = artistDao.getObjectList();
	return songDAO.getObjectList(artistList);
    }
}
