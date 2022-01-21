package com.spotifi.app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.spotifi.app.controller.jbdcservice.connection.DBConnection;
import com.spotifi.app.model.dao.interfaces.IDAO;
import com.spotifi.app.model.entities.Genre;

public class GenreDAO implements IDAO<Genre> {
    private Connection connection;
    private static final Logger LOG = Logger.getLogger(GenreDAO.class.getName());

    public GenreDAO(DBConnection DBConnection) {
	super();
	connection = DBConnection.connection();
    }

    @Override
    public void add(Genre genre) throws SQLException {
	PreparedStatement statement = connection.prepareStatement("INSERT INTO genre(genre_name) VALUES(?)");
	statement.setString(1, genre.getName());
	try {
	    statement.executeUpdate();
	    getObjectList();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	} finally {
	    statement.close();
	}
    }

    @Override
    public void update(Genre genre) throws SQLException {
	PreparedStatement statement = connection.prepareStatement("UPDATE genre SET genre_name=? WHERE genre_id=?");
	statement.setString(1, genre.getName());
	statement.setInt(2, genre.getId());

	try {
	    statement.executeUpdate();
	    getObjectList();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	} finally {
	    statement.close();
	}
    }

    @Override
    public void delete(int id) throws SQLException {
	PreparedStatement statement = connection.prepareStatement("DELETE FROM genre WHERE genre_id= ?");
	statement.setInt(1, id);

	try {
	    statement.executeUpdate();
	    getObjectList();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	} finally {
	    statement.close();
	}

    }

    @Override
    public List<Genre> getObjectList() throws SQLException {
	List<Genre> genreList = new ArrayList<Genre>();
	Statement statement = connection.createStatement();
	boolean res = statement.execute("select * from genre");
	if (res) {
	    ResultSet resultSet = statement.getResultSet();
	    while (resultSet.next()) {
		Genre genre = new Genre();
		genre.setId(resultSet.getInt("genre_id"));
		genre.setName(resultSet.getString("genre_name"));
		genreList.add(genre);
	    }
	}
	statement.close();
	return genreList;
    }

    @Override
    public Genre getObject(int id) throws SQLException {
	List<Genre> genreList = getObjectList();
	for (Genre genre : genreList) {
	    if (genre.getId() == id) {
		return genre;
	    }
	}
	return null;
    }
}
