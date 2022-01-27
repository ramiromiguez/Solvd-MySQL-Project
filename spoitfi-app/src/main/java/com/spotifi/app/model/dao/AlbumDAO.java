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
import com.spotifi.app.model.entities.AlbumAsDB;
import com.spotifi.app.model.entities.Song;

public class AlbumDAO implements IDAO<AlbumAsDB> {
    private Connection connection;
    private static final Logger LOG = Logger.getLogger(AlbumDAO.class.getName());

    public AlbumDAO(DBConnection DBConnection) {
	super();
	connection = DBConnection.connection();
    }

    @Override
    public void add(AlbumAsDB album) {
	try {
	    PreparedStatement statement = connection.prepareStatement(
		    "INSERT INTO album(album_name, artist_id, genre_id, album_length) VALUES(?, ?, ?, ?)");
	    statement.setString(1, album.getName());
	    statement.setInt(2, album.getArtistId());
	    statement.setInt(3, album.getGenreId());
	    statement.setInt(4, album.getLength());
	    statement.executeUpdate();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
    }

    public void addSong(int albumId, int songId) {
	try {
	    PreparedStatement statement = connection
		    .prepareStatement("INSERT INTO album_content(album_id, user_id) VALUES(?, ?)");
	    statement.setInt(1, albumId);
	    statement.setInt(2, songId);
	    statement.executeUpdate();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
    }

    public void addListOfSongs(int albumId, List<Song> songs) {
	for (Song song : songs) {
	    try {
		PreparedStatement statement = connection
			.prepareStatement("INSERT INTO album_content(album_id, song_id) VALUES(?, ?)");
		statement.setInt(1, albumId);
		statement.setInt(2, song.getId());
		statement.executeUpdate();
	    } catch (SQLException e) {
		LOG.log(Level.WARNING, e.getMessage());
	    }
	}
    }

    public void updateAlbumSongs(int albumId, List<Song> newContent) throws SQLException {
	try {
	    PreparedStatement statement = connection.prepareStatement("DELETE FROM album_content WHERE album_id=?");
	    statement.setInt(1, albumId);
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
	try {
	    for (Song song : newContent) {
		PreparedStatement statement = connection
			.prepareStatement("INSERT INTO album_content(album_id, song_id) VALUES (?,?)");
		statement.setInt(1, albumId);
		statement.setInt(2, song.getId());
		statement.executeUpdate();
	    }
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
    }

    @Override
    public void update(AlbumAsDB album) throws SQLException {
	try {
	    PreparedStatement statement = connection.prepareStatement(
		    "UPDATE album SET album_name=?, artist_id=?, genre_id=?, album_length=? WHERE album_id=?");
	    statement.setString(1, album.getName());
	    statement.setInt(2, album.getArtistId());
	    statement.setInt(3, album.getId());
	    statement.setInt(4, album.getLength());
	    statement.setInt(5, album.getId());
	    statement.executeUpdate();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
    }

    @Override
    public void delete(int albumId) throws SQLException {
	try {
	    connection.setAutoCommit(false);
	    PreparedStatement statement = connection.prepareStatement("DELETE FROM album_content WHERE album_id=?");
	    statement.setInt(1, albumId);
	    statement.executeUpdate();
	    statement = connection.prepareStatement("DELETE FROM album WHERE album_id = ?;");
	    statement.setInt(1, albumId);
	    statement.executeUpdate();
	    connection.commit();
	} catch (SQLException e) {
	    connection.rollback();
	    LOG.log(Level.WARNING, e.getMessage());
	}
    }

    public void deleteSong(int albumId, int songId) {
	try {
	    PreparedStatement statement = connection
		    .prepareStatement("DELETE FROM album_content WHERE album_id=? AND song_id=?");
	    statement.setInt(1, albumId);
	    statement.setInt(2, songId);
	    statement.executeUpdate();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
    }

    public void deleteListOfSongs(int albumId, List<Song> songs) {
	for (Song song : songs) {
	    try {
		PreparedStatement statement = connection
			.prepareStatement("DELETE FROM album_content WHERE album_id=? AND song_id=?");
		statement.setInt(1, albumId);
		statement.setInt(2, song.getId());
		statement.executeUpdate();
	    } catch (SQLException e) {
		LOG.log(Level.WARNING, e.getMessage());
	    }
	}
    }

    public List<Integer> getAlbumSongsId(int albumId) throws SQLException {
	List<Integer> songsId = new ArrayList<>();
	try {
	    PreparedStatement prepareStatement = connection
		    .prepareStatement("SELECT song_id FROM album_content WHERE album_id=?");
	    prepareStatement.setInt(1, albumId);
	    ResultSet res = prepareStatement.executeQuery();
	    while (res.next()) {
		songsId.add(res.getInt("song_id"));
	    }
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
	return songsId;
    }

    @Override
    public List<AlbumAsDB> getObjectList() throws SQLException {
	List<AlbumAsDB> albumList = new ArrayList<AlbumAsDB>();

	try {
	    Statement statement = connection.createStatement();
	    boolean existalbum = statement
		    .execute("SELECT album_id, album_name, genre_id, artist_id, album_length FROM album");
	    if (existalbum) {
		ResultSet resultSetAlbum = statement.getResultSet();
		while (resultSetAlbum.next()) {
		    AlbumAsDB albumAsDB = new AlbumAsDB();
		    albumAsDB.setId(resultSetAlbum.getInt("album_id"));
		    albumAsDB.setName(resultSetAlbum.getString("album_name"));
		    albumAsDB.setLength(resultSetAlbum.getInt("album_length"));
		    albumAsDB.setGenreId(resultSetAlbum.getInt("genre_id"));
		    albumAsDB.setArtistId(resultSetAlbum.getInt("artist_id"));
		    albumList.add(albumAsDB);
		}
	    }
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
	return albumList;
    }

    @Override
    public AlbumAsDB getObject(int id) throws SQLException {
	List<AlbumAsDB> albumList = getObjectList();
	for (AlbumAsDB albumAsDB : albumList) {
	    if (albumAsDB.getId() == id) {
		return albumAsDB;
	    }
	}
	return null;
    }
}
