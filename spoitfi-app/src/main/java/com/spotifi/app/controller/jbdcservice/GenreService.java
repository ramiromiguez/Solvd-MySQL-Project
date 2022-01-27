package com.spotifi.app.controller.jbdcservice;

import java.sql.SQLException;
import java.util.List;

import com.spotifi.app.controller.jbdcservice.connection.DBConnection;
import com.spotifi.app.model.dao.GenreDAO;
import com.spotifi.app.model.dao.interfaces.IDAO;
import com.spotifi.app.model.entities.Genre;

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
