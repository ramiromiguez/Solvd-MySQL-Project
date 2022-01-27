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
import com.spotifi.app.model.entities.ArtistAsDB;

public class ArtistDAO implements IDAO<ArtistAsDB> {

    private Connection connection;
    private static final Logger LOG = Logger.getLogger(AlbumDAO.class.getName());

    public ArtistDAO(DBConnection DBConnection) {
	connection = DBConnection.connection();
    }

    @Override
    public void add(ArtistAsDB artistAsDB) throws SQLException {
	try {
	    PreparedStatement statement = connection
		    .prepareStatement("INSERT INTO artist(artist_name, country_id, genre_id) VALUES(?, ?, ?)");
	    statement.setString(1, artistAsDB.getName());
	    statement.setInt(2, artistAsDB.getCountryId());
	    statement.setInt(3, artistAsDB.getGenreId());
	    statement.executeUpdate();

	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
    }

    @Override
    public void update(ArtistAsDB artistAsDB) throws SQLException {
	try {
	    PreparedStatement statement = connection
		    .prepareStatement("UPDATE artist SET artist_name=?, country_id=?, genre_id=? WHERE artist_id=?");
	    statement.setString(1, artistAsDB.getName());
	    statement.setInt(2, artistAsDB.getCountryId());
	    statement.setInt(3, artistAsDB.getGenreId());
	    statement.setInt(4, artistAsDB.getId());
	    statement.executeUpdate();

	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
    }

    public void delete(int artistId) throws SQLException {
	try {
	    PreparedStatement statement = connection.prepareStatement("DELETE FROM artist WHERE artist_id =?");
	    statement.setInt(1, artistId);
	    statement.executeUpdate();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	    connection.rollback();
	}
    }

    public List<Integer> getAlbumListId(int artistId) throws SQLException {
	List<Integer> albumsId = new ArrayList<>();

	try {
	    PreparedStatement prepareStatement = connection
		    .prepareStatement("SELECT album_id FROM album WHERE artist_id=?");
	    prepareStatement.setInt(1, artistId);
	    ResultSet res = prepareStatement.executeQuery();
	    if (res != null) {
		while (res.next()) {
		    albumsId.add(res.getInt("album_id"));
		}
	    }
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
	albumsId.forEach(x -> x.toString());
	return albumsId;
    }

    public List<Integer> getSingleList(int artistId) throws SQLException {
	List<Integer> singlesId = new ArrayList<>();

	try {
	    PreparedStatement prepareStatement = connection
		    .prepareStatement("SELECT song_id FROM artist_singles WHERE artist_id=?");
	    prepareStatement.setInt(1, artistId);
	    ResultSet res = prepareStatement.executeQuery();
	    while (res.next()) {
		singlesId.add(res.getInt("song_id"));
	    }
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
	return singlesId;
    }

    public List<ArtistAsDB> getObjectList() throws SQLException {
	List<ArtistAsDB> artistsList = new ArrayList<ArtistAsDB>();
	try {
	    Statement statement = connection.createStatement();
	    boolean res = statement.execute("select * from artist");
	    if (res) {
		ResultSet resultSet = statement.getResultSet();
		while (resultSet.next()) {
		    ArtistAsDB artistAsDB = new ArtistAsDB();
		    artistAsDB.setId(resultSet.getInt("artist_id"));
		    artistAsDB.setName(resultSet.getString("artist_name"));
		    artistAsDB.setCountryId(resultSet.getInt("country_id"));
		    artistAsDB.setGenreId(resultSet.getInt("genre_id"));
		    artistsList.add(artistAsDB);
		}
	    }
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
	return artistsList;
    }

    public ArtistAsDB getObject(int id) throws SQLException {
	List<ArtistAsDB> artistsList = getObjectList();
	for (ArtistAsDB artistAsDB : artistsList) {
	    if (artistAsDB.getId() == id) {
		return artistAsDB;
	    }
	}
	return null;
    }
}
