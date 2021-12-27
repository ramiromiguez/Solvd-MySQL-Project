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
import com.spotifi.app.objects.Artist;

public class ArtistDAO implements IDAO<Artist> {

    private DBConnection DBConnection;
    private static final Logger LOG = Logger.getLogger(AlbumDAO.class.getName());

    public ArtistDAO(DBConnection DBConnection) throws SQLException {
	this.DBConnection = DBConnection;
    }

    @Override
    public void add(Artist artist) throws SQLException {
	Connection connection = DBConnection.connection();
	PreparedStatement statement = connection
		.prepareStatement("INSERT INTO artist(artist_name, country_id, genre_id) VALUES(?, ?, ?)");
	statement.setString(1, artist.getName());
	statement.setInt(2, artist.getCountryId());
	statement.setInt(3, artist.getGenreId());
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
    public void update(Artist artist) throws SQLException {
	Connection connection = DBConnection.connection();
	PreparedStatement statement = connection
		.prepareStatement("UPDATE artist SET artist_name=?, country_id=?, genre_id=? WHERE artist_id=?");
	statement.setString(1, artist.getName());
	statement.setInt(2, artist.getCountryId());
	statement.setInt(3, artist.getGenreId());
	statement.setInt(4, artist.getId());
	try {
	    statement.executeUpdate();
	    getObjectList();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	} finally {
	    statement.closeOnCompletion();
	}

    }

    public void delete(int artistId) throws SQLException {
	Connection connection = DBConnection.connection();
	AlbumDAO albumDAO = new AlbumDAO(DBConnection);
	SongDAO songDAO = new SongDAO(DBConnection);

	try {
	    PreparedStatement statement = connection.prepareStatement("SELECT album_id FROM album WHERE artist_id = ?");
	    statement.setInt(1, artistId);
	    ResultSet res = statement.executeQuery();
	    while (res.next()) {
		albumDAO.delete(res.getInt("album_id"));
	    }

	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
	try {
	    PreparedStatement statement = connection.prepareStatement("SELECT song_id FROM song WHERE artist_id = ?");
	    statement.setInt(1, artistId);
	    ResultSet res = statement.executeQuery();
	    while (res.next()) {
		songDAO.delete(res.getInt("song_id"));
	    }

	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}

	try {
	    PreparedStatement statement = connection.prepareStatement("DELETE FROM artist WHERE artist_id =?");
	    statement.setInt(1, artistId);
	    statement.executeUpdate();
	    getObjectList();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	    connection.rollback();
	}
    }

    @Override
    public List<Artist> getObjectList() throws SQLException {
	List<Artist> artistsList = new ArrayList<Artist>();
	Connection connection = DBConnection.connection();
	Statement statement = connection.createStatement();
	boolean res = statement.execute("select * from artist");
	if (res) {
	    ResultSet resultSet = statement.getResultSet();
	    while (resultSet.next()) {
		Artist artist = new Artist();
		artist.setId(resultSet.getInt("artist_id"));
		artist.setName(resultSet.getString("artist_name"));
		artist.setCountryId(resultSet.getInt("country_id"));
		artist.setGenreId(resultSet.getInt("genre_id"));
		artistsList.add(artist);
	    }
	}
	return artistsList;
    }

    @Override
    public Artist getObject(int id) throws SQLException {
	List<Artist> artistsList = getObjectList();
	for (Artist artist : artistsList) {
	    if (artist.getId() == id) {
		return artist;
	    }
	}
	return null;
    }
}
