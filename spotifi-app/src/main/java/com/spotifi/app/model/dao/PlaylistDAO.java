package com.spotifi.app.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.spotifi.app.controller.jbdcservice.SongService;
import com.spotifi.app.controller.jbdcservice.connection.DBConnection;
import com.spotifi.app.model.dao.interfaces.IDAO;
import com.spotifi.app.model.entities.Playlist;
import com.spotifi.app.model.entities.Song;

public class PlaylistDAO implements IDAO<Playlist> {
    private Connection connection;
    private static final Logger LOG = Logger.getLogger(SongDAO.class.getName());

    public PlaylistDAO(DBConnection DBConnection) {
	super();
	connection = DBConnection.connection();
    }

    @Override
    public void add(Playlist playlist) throws SQLException {
	PreparedStatement statement = connection
		.prepareStatement("INSERT INTO playlist(playlist_name, playlist_date, user_id) VALUES(?, ?, ?)");
	statement.setString(1, playlist.getName());
	statement.setDate(2, (Date) playlist.getCreationDate());
	statement.setInt(3, playlist.getUser().getId());

	try {
	    statement.executeUpdate();
	    getObjectList();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	} finally {
	    statement.close();
	}

    }

    public void addSong(int playlistId, int songId) {
	try {
	    PreparedStatement statement = connection
		    .prepareStatement("INSERT INTO playlist_content(playlist_id, song_id) VALUES(?, ?)");
	    statement.setInt(1, playlistId);
	    statement.setInt(2, songId);
	    statement.executeUpdate();
	    getObjectList();
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
		getObjectList();
	    } catch (SQLException e) {
		LOG.log(Level.WARNING, e.getMessage());
	    }
	}
    }

    private void updatePlaylistContent(int playlistId, List<Song> newContent, Connection connection)
	    throws SQLException {
	try {
	    PreparedStatement statement = connection.prepareStatement("DELETE FROM playlist_content WHERE song_id=?");
	    statement.setInt(1, playlistId);
	    getObjectList();
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
	    getObjectList();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
    }

    @Override
    public void update(Playlist playlist) throws SQLException {
	PreparedStatement statement = connection.prepareStatement(
		"UPDATE playlist SET playlist_name=?, playlist_date=?, user_id=? WHERE playlist_id=?");
	statement.setString(1, playlist.getName());
	statement.setDate(2, (Date) playlist.getCreationDate());
	statement.setInt(3, playlist.getUser().getId());
	if (playlist.getPlaylistSongs().size() > 0) {
	    updatePlaylistContent(playlist.getId(), playlist.getPlaylistSongs(), connection);
	}
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
    public void delete(int id) throws SQLException {
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

    private List<Song> getPlaylistSongs(int playlistId) throws SQLException {
	List<Integer> songsId = new ArrayList<>();
	List<Song> songsInPlaylist = new ArrayList<>();
	try {
	    PreparedStatement prepareStatement = connection
		    .prepareStatement("SELECT song_id FROM playlist_content WHERE playlist_id=?");
	    prepareStatement.setInt(1, playlistId);
	    ResultSet res = prepareStatement.executeQuery();
	    while (res.next()) {
		songsId.add(res.getInt("song_id"));
	    }
	    prepareStatement.closeOnCompletion();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
	for (int id : songsId) {
	    SongService songService = new SongService();
	    songsInPlaylist.add(songService.getObject(id));
	}
	return songsInPlaylist;
    }

    public List<Playlist> getObjectList() throws SQLException {
	List<Playlist> playlistList = new ArrayList<Playlist>();
	UserDAO userDao = new UserDAO(new DBConnection());
	PreparedStatement statement = connection.prepareStatement("select * from playlist");
	ResultSet res = statement.executeQuery();
	if (res != null) {
	    ResultSet resultSet = statement.getResultSet();
	    while (resultSet.next()) {
		Playlist playlist = new Playlist();
		playlist.setId(resultSet.getInt("playlist_id"));
		statement = connection.prepareStatement("select song_id from playlist_content where playlist_id=?");
		playlist.setPlaylistSongs(getPlaylistSongs(playlist.getId()));
		playlist.setName(resultSet.getString("playlist_name"));
		playlist.setCreationDate(resultSet.getDate("playlist_date"));
		playlist.setUser(userDao.getObject(resultSet.getInt("user_id")));
		playlistList.add(playlist);
	    }
	}
	statement.close();
	return playlistList;
    }

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
