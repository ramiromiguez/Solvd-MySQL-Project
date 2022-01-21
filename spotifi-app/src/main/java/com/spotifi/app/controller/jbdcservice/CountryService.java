package com.spotifi.app.controller.jbdcservice;

import java.sql.SQLException;
import java.util.List;

import com.spotifi.app.controller.jbdcservice.connection.DBConnection;
import com.spotifi.app.model.dao.CountryDAO;
import com.spotifi.app.model.dao.interfaces.IDAO;
import com.spotifi.app.model.entities.Country;

public class CountryService implements IDAO<Country> {
    private CountryDAO countryDao = new CountryDAO(new DBConnection());

    @Override
    public void add(Country country) throws SQLException {
	countryDao.add(country);
    }

    @Override
    public void update(Country country) throws SQLException {
	countryDao.update(country);
    }

    @Override
    public void delete(int id) throws SQLException {
	countryDao.delete(id);
    }

    @Override
    public Country getObject(int id) throws SQLException {
	return countryDao.getObject(id);
    }

    @Override
    public List<Country> getObjectList() throws SQLException {
	return countryDao.getObjectList();
    }
}
