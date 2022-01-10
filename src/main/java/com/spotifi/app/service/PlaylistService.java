package com.spotifi.app.service;

import java.sql.SQLException;
import java.util.List;

import com.spotifi.app.connection.DBConnection;
import com.spotifi.app.dao.PlaylistDAO;
import com.spotifi.app.interfaces.IDAO;
import com.spotifi.app.objects.Playlist;
import com.spotifi.app.objects.Song;

public class PlaylistService implements IDAO<Playlist> {

    private PlaylistDAO playlistDao = new PlaylistDAO(new DBConnection());

    @Override
    public void add(Playlist playlist) throws SQLException {
	playlistDao.add(playlist);
    }

    public void addSong(int playlistId, int songId) throws SQLException {
	playlistDao.addSong(playlistId, songId);
    }

    public void addSong(Playlist playlist, Song song) throws SQLException {
	playlistDao.addSong(playlist.getId(), song.getId());
    }

    public void addListOfSongs(Playlist playlist, List<Song> listOfSongs) throws SQLException {
	playlistDao.addListOfSongs(playlist.getId(), listOfSongs);
    }

    public void addListOfSongs(int playlistId, List<Song> listOfSongs) throws SQLException {
	playlistDao.addListOfSongs(playlistId, listOfSongs);
    }

    @Override
    public void update(Playlist playlist) throws SQLException {
	playlistDao.update(playlist);
    }

    @Override
    public void delete(int id) throws SQLException {
	playlistDao.delete(id);
    }

    public void deleteSong(int playlistId, int songId) throws SQLException {
	playlistDao.deleteSong(playlistId, songId);
    }

    public void deleteSong(Playlist playlist, Song song) throws SQLException {
	playlistDao.deleteSong(playlist.getId(), song.getId());
    }

    public void deleteListOfSongs(Playlist playlist, List<Song> listOfSongs) throws SQLException {
	playlistDao.deleteListOfSongs(playlist.getId(), listOfSongs);
    }

    public void deleteListOfSongs(int playlistId, List<Song> listOfSongs) throws SQLException {
	playlistDao.deleteListOfSongs(playlistId, listOfSongs);
    }

    @Override
    public Playlist getObject(int id) throws SQLException {
	return playlistDao.getObject(id);
    }

    @Override
    public List<Playlist> getObjectList() throws SQLException {
	return playlistDao.getObjectList();
    }
}
