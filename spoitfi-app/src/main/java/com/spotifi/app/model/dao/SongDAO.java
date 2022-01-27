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
import com.spotifi.app.model.entities.SongAsDB;

public class SongDAO implements IDAO<SongAsDB> {
    private Connection connection;
    private static final Logger LOG = Logger.getLogger(SongDAO.class.getName());

    public SongDAO(DBConnection DBConnection) {
	super();
	connection = DBConnection.connection();
    }

    @Override
    public void add(SongAsDB songAsDB) throws SQLException {
	try {
	    PreparedStatement statement = connection.prepareStatement(
		    "INSERT INTO song(song_name, song_duration, artist_id, genre_id) VALUES(?, ?, ?, ?)");
	    statement.setString(1, songAsDB.getName());
	    statement.setFloat(2, songAsDB.getDuration());
	    statement.setInt(3, songAsDB.getArtistId());
	    statement.setInt(4, songAsDB.getGenreId());
	    statement.executeUpdate();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
    }

    @Override
    public void update(SongAsDB songAsDB) throws SQLException {
	PreparedStatement statement = connection.prepareStatement(
		"UPDATE song SET song_name=?, song_duration=?, artist_id=?, genre_id=? WHERE song_id=?");
	statement.setString(1, songAsDB.getName());
	statement.setFloat(2, songAsDB.getDuration());
	statement.setInt(3, songAsDB.getGenreId());
	statement.setInt(4, songAsDB.getArtistId());
	statement.setInt(5, songAsDB.getId());
	try {
	    statement.executeUpdate();
	    getObjectList();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	} finally {

	}
    }

    @Override
    public void delete(int id) throws SQLException {
	try {
	    connection.setAutoCommit(false);
	    PreparedStatement statement = connection.prepareStatement("DELETE FROM song_liked WHERE song_id = ?;");
	    statement.setInt(1, id);
	    statement.executeUpdate();
	    statement = connection.prepareStatement("DELETE FROM artist_singles WHERE song_id = ?;");
	    statement.setInt(1, id);
	    statement.executeUpdate();
	    statement = connection.prepareStatement("DELETE FROM album_content WHERE song_id = ?;");
	    statement.setInt(1, id);
	    statement.executeUpdate();
	    statement = connection.prepareStatement("DELETE FROM playlist_content WHERE song_id = ?;");
	    statement.setInt(1, id);
	    statement.executeUpdate();
	    statement = connection.prepareStatement("DELETE FROM song WHERE song_id= ?;");
	    statement.setInt(1, id);
	    statement.executeUpdate();
	    connection.commit();

	} catch (SQLException e) {
	    connection.rollback();
	    LOG.log(Level.WARNING, e.getMessage());
	}
    }

    @Override
    public List<SongAsDB> getObjectList() throws SQLException {
	List<SongAsDB> songList = new ArrayList<SongAsDB>();
	try {
	    Statement statement = connection.createStatement();
	    boolean res = statement.execute("select * from song");
	    if (res) {
		ResultSet resultSet = statement.getResultSet();
		while (resultSet.next()) {
		    SongAsDB songAsDB = new SongAsDB();
		    songAsDB.setId(resultSet.getInt("song_id"));
		    songAsDB.setName(resultSet.getString("song_name"));
		    songAsDB.setDuration(resultSet.getFloat("song_duration"));
		    songAsDB.setGenreId(resultSet.getInt("genre_id"));
		    songAsDB.setArtistId(resultSet.getInt("artist_id"));
		    songList.add(songAsDB);
		}
	    }
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
	return songList;
    }

    @Override
    public SongAsDB getObject(int id) throws SQLException {
	List<SongAsDB> songList = getObjectList();
	for (SongAsDB songAsDB : songList) {
	    if (songAsDB.getId() == id) {
		return songAsDB;
	    }
	}
	return null;
    }
}
