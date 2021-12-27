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
import com.spotifi.app.interfaces.IDAO;
import com.spotifi.app.objects.User;

public class UserDAO implements IDAO<User> {
    private DBConnection DBConnection;
    private static final Logger LOG = Logger.getLogger(SongDAO.class.getName());

    public UserDAO(DBConnection DBConnection) {
	super();
	this.DBConnection = DBConnection;
    }

    @Override
    public void add(User user) throws SQLException {
	Connection connection = DBConnection.connection();
	PreparedStatement statement = connection
		.prepareStatement("INSERT INTO user(user_name, user_birth) VALUES(?, ?)");
	statement.setString(1, user.getName());
	statement.setDate(4, (Date) user.getBirth());
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
    public void update(User user) throws SQLException {
	Connection connection = DBConnection.connection();
	PreparedStatement statement = connection
		.prepareStatement("UPDATE user SET user_name=?, user_birth=? WHERE user_id=?");
	statement.setString(1, user.getName());
	statement.setDate(2, (Date) user.getBirth());
	statement.setInt(3, user.getId());

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
	Connection connection = DBConnection.connection();
	PlaylistDAO playlist = new PlaylistDAO(DBConnection);
	connection.setAutoCommit(false);

	PreparedStatement statement = connection.prepareStatement("SELECT playlist_id FROM playlist WHERE user_id= ?;");
	statement.setInt(1, id);
	ResultSet res = statement.executeQuery();
	while (res.next()) {
	    playlist.delete(res.getInt("playlist_id"));
	}
	statement = connection.prepareStatement("DELETE FROM user_followed WHERE user_id= ?;");
	statement.setInt(1, id);
	statement.executeUpdate();
	statement = connection.prepareStatement("DELETE FROM song_liked WHERE user_id= ?;");
	statement.setInt(1, id);
	statement.executeUpdate();
	try {
	    connection.commit();
	    getObjectList();
	} catch (SQLException e) {
	    connection.rollback();
	    LOG.log(Level.WARNING, e.getMessage());
	}

    }

    @Override
    public List<User> getObjectList() throws SQLException {
	Connection connection = DBConnection.connection();
	List<User> userList = new ArrayList<User>();
	Statement statement = connection.createStatement();
	boolean res = statement.execute("select * from user");
	if (res) {
	    ResultSet resultSet = statement.getResultSet();
	    while (resultSet.next()) {
		User user = new User();
		user.setId(resultSet.getInt("user_id"));
		user.setName(resultSet.getString("user_name"));
		user.setBirth(resultSet.getDate("user_birth"));
		userList.add(user);
	    }
	}
	statement.close();
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
