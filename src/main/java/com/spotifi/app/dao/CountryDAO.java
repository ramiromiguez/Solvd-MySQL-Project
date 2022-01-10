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
import com.spotifi.app.interfaces.IDAO;
import com.spotifi.app.objects.Country;

public class CountryDAO implements IDAO<Country> {
    private Connection connection;
    private static final Logger LOG = Logger.getLogger(CountryDAO.class.getName());

    public CountryDAO(DBConnection DBConnection) {
	super();
	connection = DBConnection.connection();
    }

    @Override
    public void add(Country country) throws SQLException {
	PreparedStatement statement = connection.prepareStatement("INSERT INTO country(country_name) VALUES(?)");
	statement.setString(1, country.getName());
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
    public void update(Country country) throws SQLException {
	PreparedStatement statement = connection
		.prepareStatement("UPDATE country SET country_name=? WHERE country_id=?");
	statement.setString(1, country.getName());
	statement.setInt(2, country.getId());

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
    public void delete(int id) throws SQLException {
	PreparedStatement statement = connection.prepareStatement("DELETE FROM country WHERE country_id= ?;");
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

    @Override
    public List<Country> getObjectList() throws SQLException {
	List<Country> countryList = new ArrayList<Country>();
	Statement statement = connection.createStatement();
	boolean res = statement.execute("select * from country");
	if (res) {
	    ResultSet resultSet = statement.getResultSet();
	    while (resultSet.next()) {
		Country country = new Country();
		country.setId(resultSet.getInt("country_id"));
		country.setName(resultSet.getString("country_name"));
		countryList.add(country);
	    }
	}
	statement.close();
	return countryList;
    }

    @Override
    public Country getObject(int id) throws SQLException {
	List<Country> countryList = getObjectList();
	for (Country country : countryList) {
	    if (country.getId() == id) {
		return country;
	    }
	}
	return null;
    }
}
