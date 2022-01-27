package com.spotifi.app.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.spotifi.app.controller.jbdcservice.connection.DBConnection;
import com.spotifi.app.model.dao.interfaces.IDAO;
import com.spotifi.app.model.entities.User;

public class UserDAO implements IDAO<User> {
    private Connection connection;
    private static final Logger LOG = Logger.getLogger(SongDAO.class.getName());

    public UserDAO(DBConnection DBConnection) {
	super();
	connection = DBConnection.connection();
    }

    @Override
    public void add(User user) throws SQLException {
	try {
	    PreparedStatement statement = connection
		    .prepareStatement("INSERT INTO user(user_name, user_birth) VALUES(?, ?)");
	    statement.setString(1, user.getName());
	    LocalDate localDate = user.getBirth();
	    Date date = Date.valueOf(localDate);
	    statement.setDate(2, date);
	    statement.executeUpdate();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
    }

    @Override
    public void update(User user) throws SQLException {
	try {
	    PreparedStatement statement = connection
		    .prepareStatement("UPDATE user SET user_name=?, user_birth=? WHERE user_id=?");
	    statement.setString(1, user.getName());
	    LocalDate localDate = user.getBirth();
	    Date date = Date.valueOf(localDate);
	    statement.setDate(2, date);
	    statement.setInt(3, user.getId());
	    statement.executeUpdate();

	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
    }

    public List<Integer> getUserPlaylistIds(int id) {
	List<Integer> playlistIds = new ArrayList<>();
	try {
	    PreparedStatement statement = connection
		    .prepareStatement("SELECT playlist_id FROM playlist WHERE user_id=?;");
	    statement.setInt(1, id);
	    ResultSet res = statement.executeQuery();
	    while (res.next()) {
		playlistIds.add(res.getInt("playlist_id"));
	    }
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
	return playlistIds;
    }

    @Override
    public void delete(int id) throws SQLException {
	connection.setAutoCommit(false);
	try {
	    connection.setAutoCommit(false);
	    PreparedStatement statement = connection.prepareStatement("DELETE FROM user_followed WHERE user_id=?;");
	    statement.setInt(1, id);
	    statement.executeUpdate();
	    statement = connection.prepareStatement("DELETE FROM song_liked WHERE user_id=?;");
	    statement.setInt(1, id);
	    statement.executeUpdate();
	    connection.commit();
	} catch (SQLException e) {
	    connection.rollback();
	    LOG.log(Level.WARNING, e.getMessage());
	}

    }

    @Override
    public List<User> getObjectList() throws SQLException {
	List<User> userList = new ArrayList<User>();
	try {
	    Statement statement = connection.createStatement();
	    boolean res = statement.execute("select * from user");
	    if (res) {
		ResultSet resultSet = statement.getResultSet();
		while (resultSet.next()) {
		    User user = new User();
		    user.setId(resultSet.getInt("user_id"));
		    user.setName(resultSet.getString("user_name"));
		    Date date = resultSet.getDate("user_birth");
		    LocalDate localDate = date.toLocalDate();
		    user.setBirth(localDate);
		    userList.add(user);
		}
	    }
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}

	return userList;
    }

    @Override
    public User getObject(int id) throws SQLException {
	List<User> userList = getObjectList();
	for (User user : userList) {
	    if (user.getId() == id) {
		return user;
	    }
	}
	return null;
    }
}
