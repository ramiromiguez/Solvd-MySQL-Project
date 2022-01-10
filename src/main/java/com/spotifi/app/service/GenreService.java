package com.spotifi.app.service;

import java.sql.SQLException;
import java.util.List;

import com.spotifi.app.connection.DBConnection;
import com.spotifi.app.dao.GenreDAO;
import com.spotifi.app.interfaces.IDAO;
import com.spotifi.app.objects.Genre;

public class GenreService implements IDAO<Genre> {
    private GenreDAO genreDao = new GenreDAO(new DBConnection());

    @Override
    public void add(Genre genre) throws SQLException {
	genreDao.add(genre);
    }

    @Override
    public void update(Genre genre) throws SQLException {
	genreDao.update(genre);
    }

    @Override
    public void delete(int id) throws SQLException {
	genreDao.delete(id);
    }

    @Override
    public Genre getObject(int id) throws SQLException {
	return genreDao.getObject(id);
    }

    @Override
    public List<Genre> getObjectList() throws SQLException {
	return genreDao.getObjectList();
    }
}
