package com.spotifi.app.controller.mybatisservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.spotifi.app.controller.mybatisservice.builder.ConnectionBuilder;
import com.spotifi.app.model.entities.Genre;
import com.spotifi.app.model.mybatismapper.GenreMapper;

public class GenreServiceMyBatis implements GenreMapper {

    @Override
    public Genre getObject(int id) {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    Genre genre;
	    genre = session.getMapper(GenreMapper.class).getObject(id);
	    session.close();
	    return genre;
	}
    }

    @Override
    public List<Genre> getObjectList() {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    List<Genre> genreList = new ArrayList<>();
	    genreList = session.getMapper(GenreMapper.class).getObjectList();
	    session.close();
	    return genreList;
	}
    }

    @Override
    public void add(Genre genre) {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    session.getMapper(GenreMapper.class).add(genre);
	    session.commit();
	    session.close();
	}
    }

    @Override
    public void update(Genre genre) {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    session.getMapper(GenreMapper.class).update(genre);
	    session.commit();
	}
    }

    @Override
    public void delete(int id) {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    session.getMapper(GenreMapper.class).delete(id);
	    session.commit();
	}
    }

}
