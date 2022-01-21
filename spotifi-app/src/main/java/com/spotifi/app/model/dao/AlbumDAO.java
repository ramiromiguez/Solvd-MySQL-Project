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
import com.spotifi.app.model.entities.Album;
import com.spotifi.app.model.entities.Artist;
import com.spotifi.app.model.entities.Song;

public class AlbumDAO implements IDAO<Album> {
    private Connection connection;
    private static final Logger LOG = Logger.getLogger(AlbumDAO.class.getName());

    public AlbumDAO(DBConnection DBConnection) {
	super();
	connection = DBConnection.connection();
    }

    @Override
    public void add(Album album) {
	try {
	    PreparedStatement statement = connection.prepareStatement(
		    "INSERT INTO album(album_name, artist_id, genre_id, album_length) VALUES(?, ?, ?, ?)");
	    statement.setString(1, album.getName());
	    statement.setInt(2, album.getArtistId());
	    statement.setInt(3, album.getGenre().getId());
	    statement.setInt(4, album.getLength());
	    statement.executeUpdate();
	    statement.close();
	    getObjectList();
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
	    getObjectList();
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
		getObjectList();
	    } catch (SQLException e) {
		LOG.log(Level.WARNING, e.getMessage());
	    }
	}
    }

    private void updateAlbumContent(int albumId, List<Song> newContent, Connection connection) throws SQLException {
	try {
	    PreparedStatement statement = connection.prepareStatement("DELETE FROM album_content WHERE album_id=?");
	    statement.setInt(1, albumId);
	    getObjectList();
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
	    getObjectList();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
    }

    @Override
    public void update(Album album) throws SQLException {
	PreparedStatement statement = connection.prepareStatement(
		"UPDATE album SET album_name=?, artist_id=?, genre_id=?, album_length=? WHERE album_id=?");
	statement.setString(1, album.getName());
	statement.setInt(2, album.getArtistId());
	statement.setInt(3, album.getGenre().getId());
	statement.setInt(4, album.getLength());
	statement.setInt(5, album.getId());
	if (album.getAlbumSongs().size() > 0) {
	    updateAlbumContent(album.getId(), album.getAlbumSongs(), connection);
	}
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

    public void deleteSong(int albumId, int songId) {
	try {
	    PreparedStatement statement = connection
		    .prepareStatement("DELETE FROM album_content WHERE album_id=? AND song_id=?");
	    statement.setInt(1, albumId);
	    statement.setInt(2, songId);
	    statement.executeUpdate();
	    getObjectList();
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
		getObjectList();
	    } catch (SQLException e) {
		LOG.log(Level.WARNING, e.getMessage());
	    }
	}
    }

    private List<Song> getAlbumSongs(int albumId) throws SQLException {
	List<Integer> songsId = new ArrayList<>();
	List<Song> songsInAlbum = new ArrayList<>();
	SongDAO songDao = new SongDAO(new DBConnection());

	try {
	    PreparedStatement prepareStatement = connection
		    .prepareStatement("SELECT song_id FROM album_content WHERE album_id= ?");
	    prepareStatement.setInt(1, albumId);
	    ResultSet res = prepareStatement.executeQuery();
	    while (res.next()) {
		songsId.add(res.getInt("song_id"));
	    }
	    prepareStatement.closeOnCompletion();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
	for (int id : songsId) {
	    songsInAlbum.add(songDao.getObject(id));
	}
	return songsInAlbum;
    }

    private Artist getArtist(List<Artist> artistList, int id) {
	for (Artist artist : artistList) {
	    if (artist.getId() == id) {
		return artist;
	    }
	}
	return null;
    }

    // implement try
    public List<Album> getObjectList(List<Artist> artistList) throws SQLException {
	Statement statement = connection.createStatement();
	List<Song> albumSongs = new ArrayList<Song>();
	List<Album> albumList = new ArrayList<Album>();
	GenreDAO genreDao = new GenreDAO(new DBConnection());
	// specify columns
	boolean existalbum = statement.execute("select * from album");
	if (existalbum) {
	    ResultSet resultSetAlbum = statement.getResultSet();

	    while (resultSetAlbum.next()) {
		Album album = new Album();
		album.setId(resultSetAlbum.getInt("album_id"));
		albumSongs = getAlbumSongs(album.getId());
		album.setAlbumSongs(albumSongs);
		album.setArtist(getArtist(artistList, resultSetAlbum.getInt("artist_id")));
		album.setName(resultSetAlbum.getString("album_name"));
		album.setLength(resultSetAlbum.getInt("album_length"));
		album.setGenre(genreDao.getObject(resultSetAlbum.getInt("genre_id")));
		albumList.add(album);
	    }
	}
	statement.close();
	return albumList;
    }

    public List<Album> getObjectList() throws SQLException {
	Statement statement = connection.createStatement();
	List<Song> albumSongs = new ArrayList<Song>();
	List<Album> albumList = new ArrayList<Album>();
	GenreDAO genreDao = new GenreDAO(new DBConnection());

	boolean existalbum = statement.execute("select * from album");
	if (existalbum) {
	    ResultSet resultSetAlbum = statement.getResultSet();

	    while (resultSetAlbum.next()) {
		Album album = new Album();
		album.setId(resultSetAlbum.getInt("album_id"));
		albumSongs = getAlbumSongs(album.getId());
		album.setAlbumSongs(albumSongs);
		album.setName(resultSetAlbum.getString("album_name"));
		album.setLength(resultSetAlbum.getInt("album_length"));
		album.setGenre(genreDao.getObject(resultSetAlbum.getInt("genre_id")));
		albumList.add(album);
	    }
	}
	statement.close();
	return albumList;
    }

    public Album getObject(int id) throws SQLException {
	List<Album> albumList = getObjectList();
	for (Album album : albumList) {
	    if (album.getId() == id) {
		return album;
	    }
	}
	return null;
    }

    public Album getObject(int id, List<Artist> artistList) throws SQLException {
	List<Album> albumList = getObjectList(artistList);
	for (Album album : albumList) {
	    if (album.getId() == id) {
		return album;
	    }
	}
	return null;
    }
}
