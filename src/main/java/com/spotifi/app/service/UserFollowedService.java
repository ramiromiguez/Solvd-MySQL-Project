package com.spotifi.app.service;

import java.sql.SQLException;
import java.util.List;

import com.spotifi.app.connection.DBConnection;
import com.spotifi.app.dao.UserFollowedDAO;
import com.spotifi.app.objects.Playlist;
import com.spotifi.app.objects.User;
import com.spotifi.app.objects.UserFollowed;

public class UserFollowedService {
    private UserFollowedDAO userFollowedDao = new UserFollowedDAO(new DBConnection());

    public void add(UserFollowed userFollowed) throws SQLException {
	userFollowedDao.add(userFollowed);
    }

    public void delete(User user, Playlist playlist) throws SQLException {
	userFollowedDao.delete(user.getId(), playlist.getId());
    }

    public void delete(int userId, int playlistId) throws SQLException {
	userFollowedDao.delete(userId, playlistId);
    }

    public UserFollowed getObject(User user, User userFollowed) throws SQLException {
	return userFollowedDao.getObject(user.getId(), userFollowed.getId());
    }

    public UserFollowed getObject(int userId, int playlistId) throws SQLException {
	return userFollowedDao.getObject(userId, playlistId);
    }

    public List<UserFollowed> getObjectList() throws SQLException {
	return userFollowedDao.getObjectList();
    }
}
