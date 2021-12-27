package com.spotifi.app.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.spotifi.app.dao.AlbumDAO;

public class DBConnection {
    private static final Logger LOG = Logger.getLogger(AlbumDAO.class.getName());

    public Connection connection() {
	Connection connection = null;

	try {
	    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spotifi", "root", "r4m1");
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
	return connection;
    }
}
