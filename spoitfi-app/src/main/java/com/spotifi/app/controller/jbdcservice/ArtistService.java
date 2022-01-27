package com.spotifi.app.controller.jbdcservice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.spotifi.app.controller.jbdcservice.connection.DBConnection;
import com.spotifi.app.model.dao.ArtistDAO;
import com.spotifi.app.model.dao.interfaces.IDAO;
import com.spotifi.app.model.entities.Album;
import com.spotifi.app.model.entities.Artist;
import com.spotifi.app.model.entities.ArtistAsDB;
import com.spotifi.app.model.entities.Song;

public class ArtistService implements IDAO<Artist> {
    private ArtistDAO artistDao = new ArtistDAO(new DBConnection());

    private ArtistAsDB objectToDataBaseObject(Artist artist) {
	ArtistAsDB artistAsDB = new ArtistAsDB();
	artistAsDB.setId(artist.getId());
	artistAsDB.setName(artist.getName());
	artistAsDB.setCountryId(artist.getCountry().getId());
	artistAsDB.setGenreId(artist.getGenre().getId());

	return artistAsDB;
    }

    @Override
    public void add(Artist artist) throws SQLException {
	ArtistAsDB artistAsDB = new ArtistAsDB();
	objectToDataBaseObject(artist);
	artistDao.add(artistAsDB);
    }

    @Override
    public void update(Artist artist) throws SQLException {
	ArtistAsDB artistAsDB = new ArtistAsDB();
	objectToDataBaseObject(artist);
	artistDao.update(artistAsDB);
    }

    @Override
    public void delete(int id) throws SQLException {
	AlbumService albumService = new AlbumService();
	SongService songService = new SongService();
	albumService.delete(id);
	songService.delete(id);
	artistDao.delete(id);
    }

    public Artist getSmallArtist(int id) throws SQLException {
	Artist artist = new Artist();
	ArtistAsDB artistAsDB = new ArtistAsDB();
	GenreService genreService = new GenreService();
	CountryService countryService = new CountryService();
	artistAsDB = artistDao.getObject(id);

	artist.setName(artistAsDB.getName());
	artist.setId(artistAsDB.getId());
	artist.setCountry(countryService.getObject(artistAsDB.getCountryId()));
	artist.setGenre(genreService.getObject(artistAsDB.getGenreId()));
	return artist;
    }

    @Override
    public Artist getObject(int id) throws SQLException {
	Artist artist = new Artist();
	ArtistAsDB artistAsDB = new ArtistAsDB();
	List<Album> albumList = new ArrayList<>();
	List<Song> singleList = new ArrayList<>();
	SongService songService = new SongService();
	GenreService genreService = new GenreService();
	AlbumService albumService = new AlbumService();
	CountryService countryService = new CountryService();

	artistAsDB = artistDao.getObject(id);
	System.out.println(artistAsDB.toString());
	List<Integer> albumsId = artistDao.getAlbumListId(artistAsDB.getId());
	if (albumsId.size() > 0) {
	    for (Integer index : albumsId) {
		Album album = new Album();
		album = albumService.getObjectWOArtist(index);
		albumList.add(album);
	    }
	}
	List<Integer> singleSongsId = artistDao.getSingleList(artistAsDB.getId());
	if (singleSongsId.size() > 0) {
	    for (Integer songId : singleSongsId) {
		Song song = new Song();
		song = songService.getObjectWOArtist(songId);
		singleList.add(song);
	    }
	}
	artist.setAlbumList(albumList);
	artist.setSingleList(singleList);
	artist.setName(artistAsDB.getName());
	artist.setId(artistAsDB.getId());
	artist.setCountry(countryService.getObject(artistAsDB.getId()));
	artist.setGenre(genreService.getObject(artistAsDB.getId()));

	return artist;
    }

    @Override
    public List<Artist> getObjectList() throws SQLException {
	Artist artist = new Artist();
	List<Artist> artistList = new ArrayList<>();
	List<ArtistAsDB> artistDBList = new ArrayList<>();
	List<Album> albumList = new ArrayList<>();
	List<Song> singleList = new ArrayList<>();
	SongService songService = new SongService();
	GenreService genreService = new GenreService();
	AlbumService albumService = new AlbumService();
	CountryService countryService = new CountryService();

	artistDBList = artistDao.getObjectList();
	for (ArtistAsDB artistAsDB : artistDBList) {
	    List<Integer> albumsId = artistDao.getAlbumListId(artistAsDB.getId());
	    if (albumsId.size() > 0) {
		for (Integer index : albumsId) {
		    Album album = new Album();
		    album = albumService.getObjectWOArtist(index);
		    albumList.add(album);
		}
	    }
	    List<Integer> singleSongsId = artistDao.getSingleList(artistAsDB.getId());
	    if (singleSongsId.size() > 0) {
		for (Integer songId : singleSongsId) {
		    Song song = new Song();
		    song = songService.getObjectWOArtist(songId);
		    singleList.add(song);
		}
	    }

	    artist.setAlbumList(albumList);
	    artist.setSingleList(singleList);
	    artist.setName(artistAsDB.getName());
	    artist.setId(artistAsDB.getId());
	    artist.setCountry(countryService.getObject(artistAsDB.getId()));
	    artist.setGenre(genreService.getObject(artistAsDB.getId()));
	    artistList.add(artist);
	}
	return artistList;
    }

}
