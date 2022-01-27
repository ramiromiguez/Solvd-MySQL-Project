package com.spotifi.app.controller.mybatisservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.spotifi.app.controller.mybatisservice.builder.ConnectionBuilder;
import com.spotifi.app.model.entities.Country;
import com.spotifi.app.model.mybatismapper.CountryMapper;

public class CountryServiceMyBatis implements CountryMapper {
    @Override
    public Country getObject(int id) {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    Country country;
	    country = session.getMapper(CountryMapper.class).getObject(id);
	    return country;
	}
    }

    @Override
    public List<Country> getObjectList() {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    List<Country> list = new ArrayList<>();
	    list = session.getMapper(CountryMapper.class).getObjectList();
	    return list;
	}
    }

    @Override
    public void add(Country country) {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    session.getMapper(CountryMapper.class).add(country);
	    session.commit();
	}
    }

    @Override
    public void update(Country country) {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    session.getMapper(CountryMapper.class).update(country);
	    session.commit();
	}
    }

    @Override
    public void delete(int id) {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    session.getMapper(CountryMapper.class).delete(id);
	    session.commit();
	}
    }
}
