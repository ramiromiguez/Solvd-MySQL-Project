package com.spotifi.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.spotifi.app.connection.DBConnection;
import com.spotifi.app.interfaces.IDAO;
import com.spotifi.app.objects.Album;

public class AlbumDAO implements IDAO<Album> {
    private DBConnection DBConnection;
    private static final Logger LOG = Logger.getLogger(AlbumDAO.class.getName());

    public AlbumDAO(DBConnection DBConnection) {
	super();
	this.DBConnection = DBConnection;
    }

    @Override
    public void add(Album album) {
	Connection connection = DBConnection.connection();
	try {
	    PreparedStatement statement = connection.prepareStatement(
		    "INSERT INTO album(album_name, artist_id, genre_id, album_length) VALUES(?, ?, ?, ?)");
	    statement.setString(1, album.getName());
	    statement.setInt(2, album.getArtistId());
	    statement.setInt(3, album.getGenreId());
	    statement.setInt(4, album.getLength());
	    statement.executeUpdate();
	    statement.close();
	    getObjectList();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
    }

    @Override
    public void update(Album album) throws SQLException {
	Connection connection = DBConnection.connection();
	PreparedStatement statement = connection.prepareStatement(
		"UPDATE album SET album_name=?, artist_id=?, genre_id=?, album_length=? WHERE album_id=?");
	statement.setString(1, album.getName());
	statement.setInt(2, album.getArtistId());
	statement.setInt(3, album.getGenreId());
	statement.setInt(4, album.getLength());
	statement.setInt(5, album.getId());
	try {
	    statement.executeUpdate();
	    getObjectList();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	} finally {
	    statement.closeOnCompletion();
	}
    }

    @Override
    public void delete(int albumId) throws SQLException {
	Connection connection = DBConnection.connection();
	connection.setAutoCommit(false);
	PreparedStatement statement = connection.prepareStatement("DELETE FROM album_content WHERE album_id = ?;");
	statement.setInt(1, albumId);
	statement.executeUpdate();
	statement = connection.prepareStatement("DELETE FROM album WHERE album_id = ?;");
	statement.setInt(1, albumId);
	statement.executeUpdate();
	try {
	    connection.commit();
	    getObjectList();
	} catch (SQLException e) {
	    connection.rollback();
	    LOG.log(Level.WARNING, e.getMessage());
	} finally {
	    statement.close();
	}
    }

    @Override
    public List<Album> getObjectList() throws SQLException {
	List<Album> albumList = new ArrayList<Album>();
	Connection connection = DBConnection.connection();
	Statement statement = connection.createStatement();
	boolean res = statement.execute("select * from album");
	if (res) {
	    ResultSet resultSet = statement.getResultSet();
	    while (resultSet.next()) {
		Album album = new Album();
		album.setId(resultSet.getInt("album_id"));
		album.setName(resultSet.getString("album_name"));
		album.setArtistId(resultSet.getInt("artist_id"));
		album.setLength(resultSet.getInt("album_length"));
		album.setGenreId(resultSet.getInt("genre_id"));
		albumList.add(album);
	    }
	}
	statement.close();
	return albumList;
    }

    @Override
    public Album getObject(int id) throws SQLException {
	List<Album> albumList = getObjectList();
	for (Album album : albumList) {
	    if (album.getId() == id) {
		return album;
	    }
	}
	return null;
    }
}
