package com.spotifi.app.service;

import java.sql.SQLException;
import java.util.List;

import com.spotifi.app.dao.SongDAO;
import com.spotifi.app.interfaces.IDAO;
import com.spotifi.app.objects.Song;

public class SongService {
    private IDAO<Song> songDAO;

    public SongService(SongDAO songDAO) {
	this.songDAO = songDAO;
    }

    public void add(Song song) throws SQLException {
	songDAO.add(song);
    }

    public void update(Song song) throws SQLException {
	songDAO.update(song);
    }

    public void delete(int id) throws SQLException {
	songDAO.delete(id);
    }

    public Song getObject(int id) throws SQLException {
	return songDAO.getObject(id);
    }

    public List<Song> getObjectList() throws SQLException {
	return songDAO.getObjectList();
    }
}
