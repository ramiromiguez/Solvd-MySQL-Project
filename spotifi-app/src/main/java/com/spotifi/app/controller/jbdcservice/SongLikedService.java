package com.spotifi.app.controller.jbdcservice;

import java.sql.SQLException;
import java.util.List;

import com.spotifi.app.controller.jbdcservice.connection.DBConnection;
import com.spotifi.app.model.dao.SongLikedDAO;
import com.spotifi.app.model.entities.SongLiked;

public class SongLikedService {
    private SongLikedDAO songLikedDao = new SongLikedDAO(new DBConnection());

    public void add(SongLiked songLiked) throws SQLException {
	songLikedDao.add(songLiked);
    }

    public void delete(int id) throws SQLException {
	songLikedDao.delete(id);
    }

    public SongLiked getObject(int id) throws SQLException {
	return songLikedDao.getObject(id);
    }

    public List<SongLiked> getObjectList() throws SQLException {
	return songLikedDao.getObjectList();
    }
}
