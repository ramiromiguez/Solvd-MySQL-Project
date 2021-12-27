package com.spotifi.app.service;

import java.sql.SQLException;
import java.util.List;

import com.spotifi.app.dao.AlbumDAO;
import com.spotifi.app.interfaces.IDAO;
import com.spotifi.app.objects.Album;

public class AlbumService {
    private IDAO<Album> albumDAO;

    public AlbumService(AlbumDAO albumDAO) {
	this.albumDAO = albumDAO;
    }

    public void add(Album album) throws SQLException {
	albumDAO.add(album);
    }

    public void update(Album album) throws SQLException {
	albumDAO.update(album);
    }

    public void delete(int id) throws SQLException {
	albumDAO.delete(id);
    }

    public Album getObject(int id) throws SQLException {
	return albumDAO.getObject(id);
    }

    public List<Album> getObjectList() throws SQLException {
	return albumDAO.getObjectList();
    }
}
