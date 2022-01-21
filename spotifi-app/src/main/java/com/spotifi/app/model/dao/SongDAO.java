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
import com.spotifi.app.model.entities.Artist;
import com.spotifi.app.model.entities.Song;

public class SongDAO implements IDAO<Song> {
    private Connection connection;
    private static final Logger LOG = Logger.getLogger(SongDAO.class.getName());

    public SongDAO(DBConnection DBConnection) {
	super();
	connection = DBConnection.connection();
    }

    @Override
    public void add(Song song) throws SQLException {
	PreparedStatement statement = connection
		.prepareStatement("INSERT INTO song(song_name, song_duration, artist_id, genre_id) VALUES(?, ?, ?, ?)");
	statement.setString(1, song.getName());
	statement.setFloat(4, song.getDuration());
	statement.setInt(2, song.getArtistId());
	statement.setInt(3, song.getGenre().getId());
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
    public void update(Song song) throws SQLException {
	PreparedStatement statement = connection.prepareStatement(
		"UPDATE song SET song_name=?, song_duration=?, artist_id=?, genre_id=? WHERE song_id=?");
	statement.setString(1, song.getName());
	statement.setFloat(2, song.getDuration());
	statement.setInt(3, song.getGenre().getId());
	statement.setInt(4, song.getArtistId());
	statement.setInt(5, song.getId());
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

    private Artist getArtist(List<Artist> artistList, int id) {
	for (Artist artist : artistList) {
	    if (artist.getId() == id) {
		return artist;
	    }
	}
	return null;
    }

    public List<Song> getObjectList() throws SQLException {
	List<Song> albumList = new ArrayList<Song>();
	Statement statement = connection.createStatement();
	GenreDAO genreDao = new GenreDAO(new DBConnection());
	boolean res = statement.execute("select * from song");
	if (res) {
	    ResultSet resultSet = statement.getResultSet();
	    while (resultSet.next()) {
		Song song = new Song();
		song.setId(resultSet.getInt("song_id"));
		song.setName(resultSet.getString("song_name"));
		song.setDuration(resultSet.getFloat("song_duration"));
		song.setGenre(genreDao.getObject(resultSet.getInt("genre_id")));
		albumList.add(song);
	    }
	}
	statement.close();
	return albumList;
    }

    public List<Song> getObjectList(List<Artist> artistList) throws SQLException {
	List<Song> albumList = new ArrayList<Song>();
	Statement statement = connection.createStatement();
	GenreDAO genreDao = new GenreDAO(new DBConnection());
	boolean res = statement.execute("select * from song");
	if (res) {
	    ResultSet resultSet = statement.getResultSet();
	    while (resultSet.next()) {
		Song song = new Song();
		song.setId(resultSet.getInt("song_id"));
		song.setName(resultSet.getString("song_name"));
		song.setArtist(getArtist(artistList, resultSet.getInt("artist_id")));
		song.setDuration(resultSet.getFloat("song_duration"));
		song.setGenre(genreDao.getObject(resultSet.getInt("genre_id")));
		albumList.add(song);
	    }
	}
	statement.close();
	return albumList;
    }

    public Song getObject(int id, List<Artist> artistList) throws SQLException {
	List<Song> songList = getObjectList(artistList);
	for (Song song : songList) {
	    if (song.getId() == id) {
		return song;
	    }
	}
	return null;
    }

    public Song getObject(int id) throws SQLException {
	List<Song> songList = getObjectList();
	for (Song song : songList) {
	    if (song.getId() == id) {
		return song;
	    }
	}
	return null;
    }
}
