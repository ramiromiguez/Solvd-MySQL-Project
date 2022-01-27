package com.spotifi.app.controller.mybatisservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.spotifi.app.controller.mybatisservice.builder.ConnectionBuilder;
import com.spotifi.app.model.entities.Album;
import com.spotifi.app.model.entities.AlbumAsDB;
import com.spotifi.app.model.entities.Song;
import com.spotifi.app.model.entities.SongAsDB;
import com.spotifi.app.model.mybatismapper.AlbumMapper;

public class AlbumServiceMyBatis implements AlbumMapper {

    public Album transformDBObjectToJavaObject(AlbumAsDB albumAsDB) {
	GenreServiceMyBatis genreService = new GenreServiceMyBatis();
	ArtistServiceMyBatis artistService = new ArtistServiceMyBatis();
	SongServiceMyBatis songService = new SongServiceMyBatis();
	List<Song> albumContent = new ArrayList<>();
	Album album = new Album();

	album.setArtist(artistService.getSmallArtist(albumAsDB.getArtistId()));
	album.setGenre(genreService.getObject(albumAsDB.getGenreId()));
	album.setId(albumAsDB.getId());
	album.setLength(albumAsDB.getLength());
	album.setName(albumAsDB.getName());
	List<SongAsDB> albumSongs = getSongsFromAlbum();
	for (SongAsDB songAsDB : albumSongs) {
	    Song song = new Song();
	    song = songService.transformDBObjectToJavaObject(songAsDB);
	    albumContent.add(song);
	}

	return album;
    }

    private AlbumAsDB transformObjectToDBObject(Album album) {
	AlbumAsDB albumAsDB = new AlbumAsDB();
	albumAsDB.setArtistId(album.getArtistId());
	albumAsDB.setLength(album.getLength());
	albumAsDB.setGenreId(album.getGenre().getId());
	albumAsDB.setId(album.getId());
	albumAsDB.setName(album.getName());
	return albumAsDB;
    }

    @Override
    public AlbumAsDB getObjectAsDB(int id) {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    AlbumAsDB albumAsDB;
	    albumAsDB = session.getMapper(AlbumMapper.class).getObjectAsDB(id);
	    return albumAsDB;
	}
    }

    public Album getObject(int id) {
	Album album = new Album();
	AlbumAsDB albumAsDB = new AlbumAsDB();
	albumAsDB = getObjectAsDB(id);
	album = transformDBObjectToJavaObject(albumAsDB);
	return album;
    }

    @Override
    public List<SongAsDB> getSongsFromAlbum() {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    List<SongAsDB> list = new ArrayList<>();
	    list = session.getMapper(AlbumMapper.class).getSongsFromAlbum();
	    return list;
	}
    }

    @Override
    public List<AlbumAsDB> getObjectAsDBList() {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    List<AlbumAsDB> list = new ArrayList<>();
	    list = session.getMapper(AlbumMapper.class).getObjectAsDBList();
	    return list;
	}
    }

    public List<Album> getObjectList() {
	List<Album> albumList = new ArrayList<>();
	List<AlbumAsDB> listAlbumAsDB = getObjectAsDBList();

	for (AlbumAsDB albumAsDB : listAlbumAsDB) {
	    Album album = new Album();
	    album = transformDBObjectToJavaObject(albumAsDB);
	    albumList.add(album);
	}
	return albumList;
    }

    @Override
    public void add(AlbumAsDB album) {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    session.getMapper(AlbumMapper.class).add(album);
	    session.commit();
	}
    }

    public void add(Album album) {
	add(transformObjectToDBObject(album));
    }

    @Override
    public void update(AlbumAsDB album) {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    session.getMapper(AlbumMapper.class).update(album);
	    session.commit();
	}
    }

    public void update(Album album) {
	update(transformObjectToDBObject(album));
    }

    @Override
    public void delete(int id) {
	try (SqlSession session = new ConnectionBuilder().buildConnection()) {
	    session.getMapper(AlbumMapper.class).delete(id);
	    session.commit();
	}
    }
}
