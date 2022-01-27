package com.spotifi.app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.spotifi.app.controller.jbdcservice.connection.DBConnection;
import com.spotifi.app.model.dao.interfaces.IDAO;
import com.spotifi.app.model.entities.PlaylistAsDB;
import com.spotifi.app.model.entities.Song;

public class PlaylistDAO implements IDAO<PlaylistAsDB> {
    private Connection connection;
    private static final Logger LOG = Logger.getLogger(SongDAO.class.getName());

    public PlaylistDAO(DBConnection DBConnection) {
	super();
	connection = DBConnection.connection();
    }

    @Override
    public void add(PlaylistAsDB playlistAsDB) throws SQLException {
	try {
	    PreparedStatement statement = connection
		    .prepareStatement("INSERT INTO playlist(playlist_name, playlist_date, user_id) VALUES(?, ?, ?)");
	    statement.setString(1, playlistAsDB.getName());
	    statement.setDate(2, playlistAsDB.getDate());
	    statement.setInt(3, playlistAsDB.getUserId());
	    statement.executeUpdate();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
    }

    public void addSong(int playlistId, int songId) {
	try {
	    PreparedStatement statement = connection
		    .prepareStatement("INSERT INTO playlist_content(playlist_id, song_id) VALUES(?, ?)");
	    statement.setInt(1, playlistId);
	    statement.setInt(2, songId);
	    statement.executeUpdate();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
    }

    public void addListOfSongs(int playlistId, List<Song> songs) {
	for (Song song : songs) {
	    try {
		PreparedStatement statement = connection
			.prepareStatement("INSERT INTO playlist_content(playlist_id, song_id) VALUES(?, ?)");
		statement.setInt(1, playlistId);
		statement.setInt(2, song.getId());
		statement.executeUpdate();
	    } catch (SQLException e) {
		LOG.log(Level.WARNING, e.getMessage());
	    }
	}
    }

    public void updatePlaylistContent(int playlistId, List<Song> newContent) throws SQLException {
	try {
	    PreparedStatement statement = connection.prepareStatement("DELETE FROM playlist_content WHERE song_id=?");
	    statement.setInt(1, playlistId);
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
	try {
	    for (Song song : newContent) {
		PreparedStatement statement = connection
			.prepareStatement("INSERT INTO playlist_content(playlist_id, song_id) VALUES (?,?)");
		statement.setInt(1, playlistId);
		statement.setInt(2, song.getId());
		statement.executeUpdate();
	    }
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
    }

    @Override
    public void update(PlaylistAsDB playlistAsDB) throws SQLException {
	try {
	    PreparedStatement statement = connection.prepareStatement(
		    "UPDATE playlist SET playlist_name=?, playlist_date=?, user_id=? WHERE playlist_id=?");
	    statement.setString(1, playlistAsDB.getName());
	    statement.setDate(2, playlistAsDB.getDate());
	    statement.setInt(3, playlistAsDB.getUserId());
	    statement.executeUpdate();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
    }

    @Override
    public void delete(int id) throws SQLException {
	try {
	    connection.setAutoCommit(false);
	    PreparedStatement statement = connection
		    .prepareStatement("DELETE FROM playlist_content WHERE playlist_id= ?;");
	    statement.setInt(1, id);
	    statement.executeUpdate();
	    statement = connection.prepareStatement("DELETE FROM playlist_followed WHERE playist_id= ?;");
	    statement.setInt(1, id);
	    statement.executeUpdate();
	    statement = connection.prepareStatement("DELETE FROM playlist WHERE playist_id= ?;");
	    statement.setInt(1, id);
	    statement.executeUpdate();
	    connection.commit();

	} catch (SQLException e) {
	    connection.rollback();
	    LOG.log(Level.WARNING, e.getMessage());
	}
    }

    public void deleteSong(int playlistId, int songId) {
	try {
	    PreparedStatement statement = connection
		    .prepareStatement("DELETE FROM playlist_content WHERE playlist_id=? AND song_id=?");
	    statement.setInt(1, playlistId);
	    statement.setInt(2, songId);
	    statement.executeUpdate();
	    getObjectList();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
    }

    public void deleteListOfSongs(int playlistId, List<Song> songs) {
	for (Song song : songs) {
	    try {
		PreparedStatement statement = connection
			.prepareStatement("DELETE FROM playlist_content WHERE playlist_id=? AND song_id=?");
		statement.setInt(1, playlistId);
		statement.setInt(2, song.getId());
		statement.executeUpdate();
		getObjectList();
	    } catch (SQLException e) {
		LOG.log(Level.WARNING, e.getMessage());
	    }
	}
    }

    public List<Integer> getPlaylistSongsId(int playlistId) throws SQLException {
	List<Integer> songsId = new ArrayList<>();
	try {
	    PreparedStatement prepareStatement = connection
		    .prepareStatement("SELECT song_id FROM playlist_content WHERE playlist_id=?");
	    prepareStatement.setInt(1, playlistId);
	    ResultSet res = prepareStatement.executeQuery();
	    while (res.next()) {
		songsId.add(res.getInt("song_id"));
	    }
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
	return songsId;
    }

    public List<PlaylistAsDB> getObjectList() throws SQLException {
	List<PlaylistAsDB> playlistList = new ArrayList<PlaylistAsDB>();

	try {
	    PreparedStatement statement = connection.prepareStatement("select * from playlist");
	    ResultSet res = statement.executeQuery();
	    if (res != null) {
		ResultSet resultSet = statement.getResultSet();
		while (resultSet.next()) {
		    PlaylistAsDB playlistAsDB = new PlaylistAsDB();
		    playlistAsDB.setId(resultSet.getInt("playlist_id"));
		    playlistAsDB.setName(resultSet.getString("playlist_name"));
		    playlistAsDB.setDate(resultSet.getDate("playlist_date"));
		    playlistAsDB.setUserId(resultSet.getInt("user_id"));
		    playlistList.add(playlistAsDB);
		}
	    }
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
	return playlistList;
    }

    public PlaylistAsDB getObject(int id) throws SQLException {
	List<PlaylistAsDB> playlistList = getObjectList();
	for (PlaylistAsDB playlistAsDB : playlistList) {
	    if (playlistAsDB.getId() == id) {
		return playlistAsDB;
	    }
	}
	return null;
    }
}
