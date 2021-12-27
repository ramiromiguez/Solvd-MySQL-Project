package com.spotifi.app.service;

import java.sql.SQLException;
import java.util.List;

import com.spotifi.app.dao.ArtistDAO;
import com.spotifi.app.interfaces.IDAO;
import com.spotifi.app.objects.Artist;

public class ArtistService {
    private IDAO<Artist> artistDAO;

    public ArtistService(ArtistDAO artistDAO) {
	this.artistDAO = artistDAO;
    }

    public void add(Artist artist) throws SQLException {
	artistDAO.add(artist);
    }

    public void update(Artist artist) throws SQLException {
	artistDAO.update(artist);
    }

    public void delete(int id) throws SQLException {
	artistDAO.delete(id);
    }

    public Artist getObject(int id) throws SQLException {
	return artistDAO.getObject(id);
    }

    public List<Artist> getObjectList() throws SQLException {
	return artistDAO.getObjectList();
    }
}
