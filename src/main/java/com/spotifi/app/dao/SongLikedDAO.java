package com.spotifi.app.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.spotifi.app.connection.DBConnection;
import com.spotifi.app.objects.Song;
import com.spotifi.app.objects.SongLiked;

public class SongLikedDAO {
    private Connection connection;
    private static final Logger LOG = Logger.getLogger(CountryDAO.class.getName());

    public SongLikedDAO(DBConnection DBConnection) {
	super();
	connection = DBConnection.connection();
    }

    public void add(SongLiked songLiked) throws SQLException {
	PreparedStatement statement = connection
		.prepareStatement("INSERT INTO song_liked(songl_date, user_id, song_id) VALUES(?,?,?)");
	statement.setDate(1, (Date) songLiked.getDate());
	statement.setInt(2, songLiked.getUser().getId());
	statement.setInt(3, songLiked.getSong().getId());
	try {
	    statement.executeUpdate();
	    getObjectList();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	} finally {
	    statement.close();
	}
    }

    public void delete(int id) throws SQLException {
	PreparedStatement statement = connection.prepareStatement("DELETE FROM song_liked WHERE song_id = ?");
	statement.setInt(1, id);

	try {
	    statement.executeUpdate();
	    getObjectList();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	} finally {
	    statement.close();
	}
    }

    public List<SongLiked> getObjectList() throws SQLException {
	List<SongLiked> songLikedList = new ArrayList<SongLiked>();
	UserDAO userDao = new UserDAO(new DBConnection());
	SongDAO songDao = new SongDAO(new DBConnection());
	Statement statement = connection.createStatement();
	boolean res = statement.execute("select * from song_liked");
	ResultSet resultSet = statement.getResultSet();
	if (res) {
	    while (resultSet.next()) {
		SongLiked songLiked = new SongLiked();
		songLiked.setDate(resultSet.getDate("songl_date"));
		songLiked.setUser(userDao.getObject(resultSet.getInt("user_id")));
		songLiked.setSong(songDao.getObject(resultSet.getInt("song_id")));
		songLikedList.add(songLiked);
	    }
	}
	statement.close();
	return songLikedList;

    }

    public SongLiked getObject(int id) throws SQLException {
	List<SongLiked> songLikedList = getObjectList();
	for (SongLiked songLiked : songLikedList) {
	    Song song = new Song();
	    song = songLiked.getSong();
	    if (song.getId() == id) {
		return songLiked;
	    }
	}
	return null;
    }

}
