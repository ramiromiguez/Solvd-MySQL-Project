package com.spotifi.app.service;

import java.sql.SQLException;
import java.util.List;

import com.spotifi.app.connection.DBConnection;
import com.spotifi.app.dao.ArtistDAO;
import com.spotifi.app.interfaces.IDAO;
import com.spotifi.app.objects.Artist;

public class ArtistService implements IDAO<Artist> {
    private ArtistDAO artistDao = new ArtistDAO(new DBConnection());

    @Override
    public void add(Artist artist) throws SQLException {
	artistDao.add(artist);
    }

    @Override
    public void update(Artist artist) throws SQLException {
	artistDao.update(artist);
    }

    @Override
    public void delete(int id) throws SQLException {
	artistDao.delete(id);
    }

    @Override
    public Artist getObject(int id) throws SQLException {
	return artistDao.getObject(id);
    }

    @Override
    public List<Artist> getObjectList() throws SQLException {
	return artistDao.getObjectList();
    }
}
