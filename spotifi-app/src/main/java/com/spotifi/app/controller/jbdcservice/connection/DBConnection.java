package com.spotifi.app.controller.jbdcservice.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    private static final Logger LOG = Logger.getLogger(DBConnection.class.getName());
    private static Connection connection;

    public Connection connection() {

	try {
	    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spotifi", "root", "r4m1");
	} catch (SQLException e) {
	    LOG.log(Level.WARNING, e.getMessage());
	}
	return connection;
    }
}
