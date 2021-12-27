package com.spotifi.app.service;

import java.sql.SQLException;
import java.util.List;

import com.spotifi.app.dao.UserDAO;
import com.spotifi.app.interfaces.IDAO;
import com.spotifi.app.objects.User;

public class UserService {
    private IDAO<User> userDAO;

    public UserService(UserDAO userDAO) {
	this.userDAO = userDAO;
    }

    public void add(User user) throws SQLException {
	userDAO.add(user);
    }

    public void update(User user) throws SQLException {
	userDAO.update(user);
    }

    public void delete(int id) throws SQLException {
	userDAO.delete(id);
    }

    public User getObject(int id) throws SQLException {
	return userDAO.getObject(id);
    }

    public List<User> getObjectList() throws SQLException {
	return userDAO.getObjectList();
    }
}
