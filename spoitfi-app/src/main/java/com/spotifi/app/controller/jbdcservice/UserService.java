package com.spotifi.app.controller.jbdcservice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.spotifi.app.controller.jbdcservice.connection.DBConnection;
import com.spotifi.app.model.dao.UserDAO;
import com.spotifi.app.model.dao.interfaces.IDAO;
import com.spotifi.app.model.entities.User;

public class UserService implements IDAO<User> {
    private UserDAO userDao = new UserDAO(new DBConnection());

    @Override
    public void add(User user) throws SQLException {
	userDao.add(user);
    }

    @Override
    public void update(User user) throws SQLException {
	userDao.update(user);
    }

    @Override
    public void delete(int id) throws SQLException {
	List<Integer> playlistIds = new ArrayList<>();
	PlaylistService playlistService = new PlaylistService();
	playlistIds = userDao.getUserPlaylistIds(id);
	for (Integer index : playlistIds) {
	    playlistService.delete(index);
	}
	userDao.delete(id);
    }

    @Override
    public User getObject(int id) throws SQLException {
	return userDao.getObject(id);
    }

    @Override
    public List<User> getObjectList() throws SQLException {
	return userDao.getObjectList();
    }
}
