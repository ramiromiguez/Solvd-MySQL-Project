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
import com.spotifi.app.objects.User;
import com.spotifi.app.objects.UserFollowed;

public class UserFollowedDAO {
    private Connection connection;
    private static final Logger LOG = Logger.getLogger(GenreDAO.class.getName());

    public UserFollowedDAO(DBConnection DBConnection) {
	super();
	connection = DBConnection.connection();
    }

    public void add(UserFollowed userFollowed) throws SQLException {
	PreparedStatement statement = connection
		.prepareStatement("INSERT INTO user_followed(user_id, userF_id) VALUES(?,?)");
	User user = userFollowed.getUser();
	User userFw = userFollowed.getUserFollowed();
	statement.setInt(1, user.getId());
	statement.setInt(2, userFw.getId());
	try {
	    statement.executeUpdate();
	    getObjectList();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	} finally {
	    statement.close();
	}
    }

    public void delete(int userId, int userFId) throws SQLException {
	PreparedStatement statement = connection
		.prepareStatement("DELETE FROM user_followed WHERE user_id= ? AND userF_id=?");
	statement.setInt(1, userId);
	statement.setInt(2, userFId);

	try {
	    statement.executeUpdate();
	    getObjectList();
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	} finally {
	    statement.close();
	}
    }

    public List<UserFollowed> getObjectList() throws SQLException {
	List<UserFollowed> userFollowedList = new ArrayList<UserFollowed>();
	Statement statement = connection.createStatement();
	UserDAO userDao = new UserDAO(new DBConnection());

	boolean res = statement.execute("select * from user_followed");
	if (res) {
	    ResultSet resultSet = statement.getResultSet();
	    while (resultSet.next()) {
		UserFollowed userFollowed = new UserFollowed();
		userFollowed.setUser(userDao.getObject(resultSet.getInt("user_id")));
		userFollowed.setUserFollowed(userDao.getObject(resultSet.getInt("userF_id")));
		userFollowedList.add(userFollowed);
	    }
	}
	statement.close();
	return userFollowedList;
    }

    public UserFollowed getObject(int userId, int userFId) throws SQLException {
	List<UserFollowed> userFollowedList = getObjectList();
	for (UserFollowed userFollowed : userFollowedList) {
	    User user = userFollowed.getUser();
	    User userFW = userFollowed.getUser();
	    if (user.getId() == userId && userFW.getId() == userFId) {
		return userFollowed;
	    }
	}
	return null;
    }
}
