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
import com.spotifi.app.objects.Playlist;
import com.spotifi.app.objects.PlaylistFollowed;
import com.spotifi.app.objects.User;

public class PlaylistFollowedDAO {
    private Connection connection;
    private static final Logger LOG = Logger.getLogger(GenreDAO.class.getName());

    public PlaylistFollowedDAO(DBConnection DBConnection) {
	super();
	connection = DBConnection.connection();
    }

    public void add(PlaylistFollowed playlistFollowed) throws SQLException {
	PreparedStatement statement = connection
		.prepareStatement("INSERT INTO playlist_content(user_id, playlist_id) VALUES(?,?)");
	User user = playlistFollowed.getUser();
	Playlist playlist = playlistFollowed.getPlaylist();
	statement.setInt(1, user.getId());
	statement.setInt(2, playlist.getId());
	try {
	    statement.executeUpdate();
	    getObjectList();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	} finally {
	    statement.close();
	}
    }

    public void delete(int userId, int playlistId) throws SQLException {
	PreparedStatement statement = connection
		.prepareStatement("DELETE FROM playlist_followed WHERE user_id= ? AND playlist_id=?");
	statement.setInt(1, userId);
	statement.setInt(2, playlistId);

	try {
	    statement.executeUpdate();
	    getObjectList();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	} finally {
	    statement.close();
	}
    }

    public List<PlaylistFollowed> getObjectList() throws SQLException {
	List<PlaylistFollowed> playlistFollowedList = new ArrayList<PlaylistFollowed>();
	Statement statement = connection.createStatement();
	UserDAO userDao = new UserDAO(new DBConnection());
	PlaylistDAO playlistDao = new PlaylistDAO(new DBConnection());
	boolean res = statement.execute("select * from playlist_followed");
	if (res) {
	    ResultSet resultSet = statement.getResultSet();
	    while (resultSet.next()) {
		PlaylistFollowed playlistFollowed = new PlaylistFollowed();
		playlistFollowed.setUser(userDao.getObject(resultSet.getInt("user_id")));
		playlistFollowed.setPlaylist(playlistDao.getObject(resultSet.getInt("playlist_id")));
		playlistFollowedList.add(playlistFollowed);
	    }
	}
	statement.close();
	return playlistFollowedList;
    }

    public PlaylistFollowed getObject(int userId, int playlistId) throws SQLException {
	List<PlaylistFollowed> playlistFollowedList = getObjectList();
	for (PlaylistFollowed playlistFollowed : playlistFollowedList) {
	    User user = playlistFollowed.getUser();
	    Playlist playlist = playlistFollowed.getPlaylist();
	    if (user.getId() == userId && playlist.getId() == playlistId) {
		return playlistFollowed;
	    }
	}
	return null;
    }
}
