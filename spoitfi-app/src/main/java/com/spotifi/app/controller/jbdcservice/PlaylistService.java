package com.spotifi.app.controller.jbdcservice;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.spotifi.app.controller.jbdcservice.connection.DBConnection;
import com.spotifi.app.model.dao.PlaylistDAO;
import com.spotifi.app.model.dao.interfaces.IDAO;
import com.spotifi.app.model.entities.Playlist;
import com.spotifi.app.model.entities.PlaylistAsDB;
import com.spotifi.app.model.entities.Song;

public class PlaylistService implements IDAO<Playlist> {

    private PlaylistDAO playlistDao = new PlaylistDAO(new DBConnection());

    private PlaylistAsDB objectToDataBaseObject(Playlist playlist) {
	PlaylistAsDB playlistAsDB = new PlaylistAsDB();

	playlistAsDB.setId(playlist.getId());
	playlistAsDB.setName(playlist.getName());
	playlistAsDB.setUserId(playlist.getUser().getId());
	playlistAsDB.setDate((Date) playlist.getCreationDate());

	return playlistAsDB;
    }

    @Override
    public void add(Playlist playlist) throws SQLException {
	PlaylistAsDB playlistAsDB = new PlaylistAsDB();
	if (playlist.getPlaylistSongs().size() > 0) {
	    playlistDao.updatePlaylistContent(playlist.getId(), playlist.getPlaylistSongs());
	}
	playlistAsDB = objectToDataBaseObject(playlist);
	playlistDao.add(playlistAsDB);
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
	PlaylistAsDB playlistAsDB = new PlaylistAsDB();
	playlistDao.updatePlaylistContent(playlist.getId(), playlist.getPlaylistSongs());
	playlistAsDB = objectToDataBaseObject(playlist);
	playlistDao.update(playlistAsDB);
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
	PlaylistAsDB playlistAsDB = new PlaylistAsDB();
	Playlist playlist = new Playlist();
	SongService songService = new SongService();
	UserService userService = new UserService();

	playlistAsDB = playlistDao.getObject(id);
	List<Song> playlistContent = new ArrayList<>();
	List<Integer> songsId = playlistDao.getPlaylistSongsId(playlistAsDB.getId());
	for (Integer index : songsId) {
	    playlistContent.add(songService.getObject(index));
	}
	playlist.setId(playlistAsDB.getId());
	playlist.setName(playlistAsDB.getName());
	playlist.setCreationDate(playlistAsDB.getDate());
	playlist.setUser(userService.getObject(playlistAsDB.getUserId()));
	playlist.setPlaylistSongs(playlistContent);

	return playlist;
    }

    @Override
    public List<Playlist> getObjectList() throws SQLException {
	List<PlaylistAsDB> playlistAsDBList = new ArrayList<>();
	List<Playlist> playlistList = new ArrayList<>();
	SongService songService = new SongService();
	UserService userService = new UserService();

	playlistAsDBList = playlistDao.getObjectList();
	for (PlaylistAsDB playlistAsDB : playlistAsDBList) {
	    Playlist playlist = new Playlist();
	    List<Song> playlistContent = new ArrayList<>();
	    List<Integer> songsId = playlistDao.getPlaylistSongsId(playlistAsDB.getId());
	    for (Integer index : songsId) {
		playlistContent.add(songService.getObject(index));
	    }
	    playlist.setId(playlistAsDB.getId());
	    playlist.setName(playlistAsDB.getName());
	    playlist.setCreationDate(playlistAsDB.getDate());
	    playlist.setUser(userService.getObject(playlistAsDB.getUserId()));
	    playlist.setPlaylistSongs(playlistContent);
	    playlistList.add(playlist);
	}
	return playlistList;
    }

}
