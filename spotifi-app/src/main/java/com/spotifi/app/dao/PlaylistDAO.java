package com.spotifi.app.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.spotifi.app.connection.DBConnection;
import com.spotifi.app.interfaces.IDAO;
import com.spotifi.app.objects.Playlist;

public class PlaylistDAO implements IDAO<Playlist> {
    private DBConnection DBConnection;
    private static final Logger LOG = Logger.getLogger(SongDAO.class.getName());

    public PlaylistDAO(DBConnection DBConnection) {
	super();
	this.DBConnection = DBConnection;
    }

    @Override
    public void add(Playlist playlist) throws SQLException {
	Connection connection = DBConnection.connection();
	PreparedStatement statement = connection
		.prepareStatement("INSERT INTO playlist(playlist_name, playlist_date, user_id) VALUES(?, ?, ?)");
	statement.setString(1, playlist.getName());
	statement.setDate(2, (Date) playlist.getCreationDate());
	statement.setInt(3, playlist.getUserId());
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
    public void update(Playlist playlist) throws SQLException {
	Connection connection = DBConnection.connection();
	PreparedStatement statement = connection.prepareStatement(
		"UPDATE playlist SET playlist_name=?, playlist_date=?, user_id=? WHERE playlist_id=?");
	statement.setString(1, playlist.getName());
	statement.setDate(2, (Date) playlist.getCreationDate());
	statement.setInt(3, playlist.getUserId());

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
	Connection connection = DBConnection.connection();
	connection.setAutoCommit(false);
	PreparedStatement statement = connection.prepareStatement("DELETE FROM playlist_content WHERE playlist_id= ?;");
	statement.setInt(1, id);
	statement.executeUpdate();
	statement = connection.prepareStatement("DELETE FROM playlist_followed WHERE playist_id= ?;");
	statement.setInt(1, id);
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
    public List<Playlist> getObjectList() throws SQLException {
	Connection connection = DBConnection.connection();
	List<Playlist> playlistList = new ArrayList<Playlist>();
	PreparedStatement statement = connection.prepareStatement("select * from playlist");
	ResultSet res = statement.executeQuery();
	if (res != null) {
	    ResultSet resultSet = statement.getResultSet();
	    while (resultSet.next()) {
		Playlist playlist = new Playlist();
		playlist.setId(resultSet.getInt("playlist_id"));
		playlist.setName(resultSet.getString("playlist_name"));
		playlist.setCreationDate(resultSet.getDate("playlist_date"));
		playlist.setUserId(resultSet.getInt("user_id"));
		playlistList.add(playlist);
	    }
	}
	statement.close();
	return playlistList;
    }

    @Override
    public Playlist getObject(int id) throws SQLException {
	List<Playlist> playlistList = getObjectList();
	for (Playlist playlist : playlistList) {
	    if (playlist.getId() == id) {
		return playlist;
	    }
	}
	return null;
    }
}
