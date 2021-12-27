package com.spotifi.app.service;

import java.sql.SQLException;
import java.util.List;

import com.spotifi.app.dao.PlaylistDAO;
import com.spotifi.app.interfaces.IDAO;
import com.spotifi.app.objects.Playlist;

public class PlaylistService {
    private IDAO<Playlist> playlistDAO;

    public PlaylistService(PlaylistDAO playlistDAO) {
	this.playlistDAO = playlistDAO;
    }

    public void add(Playlist playlist) throws SQLException {
	playlistDAO.add(playlist);
    }

    public void update(Playlist playlist) throws SQLException {
	playlistDAO.update(playlist);
    }

    public void delete(int id) throws SQLException {
	playlistDAO.delete(id);
    }

    public Playlist getObject(int id) throws SQLException {
	return playlistDAO.getObject(id);
    }

    public List<Playlist> getObjectList() throws SQLException {
	return playlistDAO.getObjectList();
    }
}
