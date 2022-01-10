package com.spotifi.app.service;

import java.sql.SQLException;
import java.util.List;

import com.spotifi.app.connection.DBConnection;
import com.spotifi.app.dao.UserDAO;
import com.spotifi.app.interfaces.IDAO;
import com.spotifi.app.objects.User;

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
