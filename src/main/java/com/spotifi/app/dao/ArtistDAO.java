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
import com.spotifi.app.objects.Artist;
import com.spotifi.app.objects.Song;

public class ArtistDAO implements IDAO<Artist> {

    private Connection connection;
    private static final Logger LOG = Logger.getLogger(AlbumDAO.class.getName());

    public ArtistDAO(DBConnection DBConnection) {
	connection = DBConnection.connection();
    }

    @Override
    public void add(Artist artist) throws SQLException {
	PreparedStatement statement = connection
		.prepareStatement("INSERT INTO artist(artist_name, country_id, genre_id) VALUES(?, ?, ?)");
	statement.setString(1, artist.getName());
	statement.setInt(2, artist.getCountry().getId());
	statement.setInt(3, artist.getGenre().getId());
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

	PreparedStatement statement = connection
		.prepareStatement("UPDATE artist SET artist_name=?, country_id=?, genre_id=? WHERE artist_id=?");
	statement.setString(1, artist.getName());
	statement.setInt(2, artist.getCountry().getId());
	statement.setInt(3, artist.getGenre().getId());
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
	AlbumDAO albumDAO = new AlbumDAO(new DBConnection());
	SongDAO songDAO = new SongDAO(new DBConnection());

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

    private List<Album> getAlbumList(int artistId) throws SQLException {
	List<Integer> albumId = new ArrayList<>();
	List<Album> artistAlbums = new ArrayList<>();
	AlbumDAO albumDao = new AlbumDAO(new DBConnection());

	try {
	    PreparedStatement prepareStatement = connection
		    .prepareStatement("SELECT album_id FROM album WHERE artist_id=?");
	    prepareStatement.setInt(1, artistId);
	    ResultSet res = prepareStatement.executeQuery();
	    while (res.next()) {
		albumId.add(res.getInt("album_id"));
	    }
	    prepareStatement.closeOnCompletion();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
	for (int id : albumId) {
	    artistAlbums.add(albumDao.getObject(id));
	}
	return artistAlbums;
    }

    private List<Song> getSingleList(int artistId) throws SQLException {
	List<Integer> singlesId = new ArrayList<>();
	List<Song> artistSingles = new ArrayList<>();
	SongDAO songDao = new SongDAO(new DBConnection());

	try {
	    PreparedStatement prepareStatement = connection
		    .prepareStatement("SELECT song_id FROM artist_singles WHERE artist_id=?");
	    prepareStatement.setInt(1, artistId);
	    ResultSet res = prepareStatement.executeQuery();
	    while (res.next()) {
		singlesId.add(res.getInt("song_id"));
	    }
	    prepareStatement.closeOnCompletion();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
	for (int id : singlesId) {
	    artistSingles.add(songDao.getObject(id));
	}
	return artistSingles;
    }

    public List<Artist> getObjectList() throws SQLException {
	List<Artist> artistsList = new ArrayList<Artist>();
	Statement statement = connection.createStatement();
	CountryDAO countryDao = new CountryDAO(new DBConnection());
	GenreDAO genreDao = new GenreDAO(new DBConnection());
	boolean res = statement.execute("select * from artist");
	if (res) {
	    ResultSet resultSet = statement.getResultSet();
	    while (resultSet.next()) {
		Artist artist = new Artist();
		artist.setId(resultSet.getInt("artist_id"));
		artist.setAlbumList(getAlbumList(artist.getId()));
		artist.setSingleList(getSingleList(artist.getId()));
		artist.setName(resultSet.getString("artist_name"));
		artist.setCountry(countryDao.getObject(resultSet.getInt("country_id")));
		artist.setGenre(genreDao.getObject(resultSet.getInt("genre_id")));
		artistsList.add(artist);
	    }
	}
	return artistsList;
    }

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
