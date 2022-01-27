package com.spotifi.app.controller.jbdcservice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.spotifi.app.controller.jbdcservice.connection.DBConnection;
import com.spotifi.app.model.dao.AlbumDAO;
import com.spotifi.app.model.dao.interfaces.IDAO;
import com.spotifi.app.model.entities.Album;
import com.spotifi.app.model.entities.AlbumAsDB;
import com.spotifi.app.model.entities.Song;

public class AlbumService implements IDAO<Album> {
    private AlbumDAO albumDao = new AlbumDAO(new DBConnection());

    private AlbumAsDB objectToDataBaseObject(Album album) {
	AlbumAsDB albumAsDB = new AlbumAsDB();
	albumAsDB.setId(album.getId());
	albumAsDB.setName(album.getName());
	albumAsDB.setLength(album.getLength());
	albumAsDB.setArtistId(album.getArtist().getId());
	albumAsDB.setGenreId(album.getGenre().getId());

	return albumAsDB;
    }

    @Override
    public void add(Album album) throws SQLException {
	AlbumAsDB albumAsDB = new AlbumAsDB();
	albumAsDB = objectToDataBaseObject(album);
	albumDao.add(albumAsDB);
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
	AlbumAsDB albumAsDB = new AlbumAsDB();
	albumAsDB = objectToDataBaseObject(album);
	albumDao.update(albumAsDB);
	if (album.getAlbumSongs().size() > 0) {
	    albumDao.updateAlbumSongs(album.getId(), album.getAlbumSongs());
	}
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
	AlbumAsDB albumAsDB = new AlbumAsDB();
	Album album = new Album();
	GenreService genreService = new GenreService();
	ArtistService artistService = new ArtistService();
	SongService songService = new SongService();
	List<Song> albumSongs = new ArrayList<>();
	List<Integer> albumSongsId = new ArrayList<>();

	albumSongsId = albumDao.getAlbumSongsId(id);
	for (Integer songId : albumSongsId) {
	    Song song = new Song();
	    song = songService.getObjectWOArtist(songId);
	    albumSongs.add(song);
	}
	album.setId(albumAsDB.getId());
	album.setName(albumAsDB.getName());
	album.setGenre(genreService.getObject(albumAsDB.getGenreId()));
	album.setId(albumAsDB.getId());
	album.setAlbumSongs(albumSongs);
	album.setArtist(artistService.getSmallArtist(albumAsDB.getArtistId()));

	return album;
    }

    public Album getObjectWOArtist(int id) throws SQLException {
	AlbumAsDB albumAsDB = new AlbumAsDB();
	Album album = new Album();
	GenreService genreService = new GenreService();
	SongService songService = new SongService();
	List<Song> albumSongs = new ArrayList<>();
	List<Integer> albumSongsId = new ArrayList<>();

	albumSongsId = albumDao.getAlbumSongsId(id);
	for (Integer songId : albumSongsId) {
	    Song song = new Song();
	    song = songService.getObjectWOArtist(songId);
	    albumSongs.add(song);
	}
	album.setId(albumAsDB.getId());
	album.setName(albumAsDB.getName());
	album.setGenre(genreService.getObject(albumAsDB.getGenreId()));
	album.setId(albumAsDB.getId());
	album.setAlbumSongs(albumSongs);

	return album;
    }

    @Override
    public List<Album> getObjectList() throws SQLException {
	List<AlbumAsDB> albumDBList = new ArrayList<>();
	List<Integer> albumSongsId = new ArrayList<>();
	List<Album> albumList = new ArrayList<>();
	SongService songService = new SongService();
	GenreService genreService = new GenreService();
	ArtistService artistService = new ArtistService();

	albumDBList = albumDao.getObjectList();
	for (AlbumAsDB albumAsDB : albumDBList) {
	    Album album = new Album();
	    List<Song> albumSongs = new ArrayList<>();
	    albumSongsId = albumDao.getAlbumSongsId(albumAsDB.getId());
	    for (Integer songId : albumSongsId) {
		Song song = new Song();
		song = songService.getObject(songId);
		albumSongs.add(song);
	    }

	    album.setId(albumAsDB.getId());
	    album.setName(albumAsDB.getName());
	    album.setGenre(genreService.getObject(albumAsDB.getGenreId()));
	    album.setId(albumAsDB.getId());
	    album.setAlbumSongs(albumSongs);
	    album.setArtist(artistService.getSmallArtist(albumAsDB.getArtistId()));
	    albumList.add(album);
	}
	return albumList;
    }
}
