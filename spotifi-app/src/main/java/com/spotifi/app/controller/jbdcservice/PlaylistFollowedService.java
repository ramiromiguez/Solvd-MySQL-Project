package com.spotifi.app.controller.jbdcservice;

import java.sql.SQLException;
import java.util.List;

import com.spotifi.app.controller.jbdcservice.connection.DBConnection;
import com.spotifi.app.model.dao.PlaylistFollowedDAO;
import com.spotifi.app.model.entities.Playlist;
import com.spotifi.app.model.entities.PlaylistFollowed;
import com.spotifi.app.model.entities.User;

public class PlaylistFollowedService {
    private PlaylistFollowedDAO playlistFollowedDao = new PlaylistFollowedDAO(new DBConnection());

    public void add(PlaylistFollowed playlistFollowed) throws SQLException {
	playlistFollowedDao.add(playlistFollowed);
    }

    public void delete(User user, Playlist playlist) throws SQLException {
	playlistFollowedDao.delete(user.getId(), playlist.getId());
    }

    public void delete(int userId, int playlistId) throws SQLException {
	playlistFollowedDao.delete(userId, playlistId);
    }

    public PlaylistFollowed getObject(User user, Playlist playlist) throws SQLException {
	return playlistFollowedDao.getObject(user.getId(), playlist.getId());
    }

    public PlaylistFollowed getObject(int userId, int playlistId) throws SQLException {
	return playlistFollowedDao.getObject(userId, playlistId);
    }

    public List<PlaylistFollowed> getObjectList() throws SQLException {
	return playlistFollowedDao.getObjectList();
    }
}
