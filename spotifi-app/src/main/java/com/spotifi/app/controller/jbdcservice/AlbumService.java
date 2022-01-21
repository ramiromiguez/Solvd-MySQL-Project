package com.spotifi.app.controller.jbdcservice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.spotifi.app.controller.jbdcservice.connection.DBConnection;
import com.spotifi.app.model.dao.AlbumDAO;
import com.spotifi.app.model.dao.ArtistDAO;
import com.spotifi.app.model.dao.interfaces.IDAO;
import com.spotifi.app.model.entities.Album;
import com.spotifi.app.model.entities.Artist;
import com.spotifi.app.model.entities.Song;

public class AlbumService implements IDAO<Album> {
    private AlbumDAO albumDao = new AlbumDAO(new DBConnection());
    private ArtistDAO artistDao = new ArtistDAO(new DBConnection());

    @Override
    public void add(Album album) throws SQLException {
	albumDao.add(album);
    }

    public void addSong(Album album, Song song) throws SQLException {
	albumDao.addSong(album.getId(), song.getId());
    }

    public void addSong(int albumId, int songId) throws SQLException {
	albumDao.addSong(albumId, songId);
    }

    public void addListOfSongs(Album album, List<Song> songsList) throws SQLException {
	albumDao.addListOfSongs(album.getId(), songsList);
    }

    public void addListOfSongs(int albumId, List<Song> songsList) throws SQLException {
	albumDao.addListOfSongs(albumId, songsList);
    }

    @Override
    public void update(Album album) throws SQLException {
	albumDao.update(album);
    }

    @Override
    public void delete(int id) throws SQLException {
	albumDao.delete(id);
    }

    public void deleteSong(int albumId, int songId) throws SQLException {
	albumDao.deleteSong(albumId, songId);
    }

    public void deleteSong(Album album, Song song) throws SQLException {
	albumDao.deleteSong(album.getId(), song.getId());
    }

    public void deleteListOfSongs(Album album, List<Song> listOfSongs) throws SQLException {
	albumDao.deleteListOfSongs(album.getId(), listOfSongs);
    }

    public void deleteListOfSongs(int albumId, List<Song> listOfSongs) throws SQLException {
	albumDao.deleteListOfSongs(albumId, listOfSongs);
    }

    @Override
    public Album getObject(int id) throws SQLException {
	List<Artist> artistList = new ArrayList<>();
	artistList = artistDao.getObjectList();
	return albumDao.getObject(id, artistList);
    }

    @Override
    public List<Album> getObjectList() throws SQLException {
	List<Artist> artistList = new ArrayList<>();
	artistList = artistDao.getObjectList();
	return albumDao.getObjectList(artistList);
    }
}
