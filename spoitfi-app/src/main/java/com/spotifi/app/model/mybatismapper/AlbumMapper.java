package com.spotifi.app.model.mybatismapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.spotifi.app.model.entities.AlbumAsDB;
import com.spotifi.app.model.entities.SongAsDB;

public interface AlbumMapper {
    final String GETOBJECT = "SELECT * FROM album WHERE album_id=#{id}";
    final String GETLISTOFOBJECTS = "SELECT * FROM album";
    final String INSERT = "INSERT INTO album(album_name, album_length, artist_id, genre_id) VALUES (#{name}, #{length}, #{artistId}, #{genreId)";
    final String UPDATE = "UPDATE album SET album_name=#{name}, album_length=#{length}, artist_id=#{artistId}, genre_id=#{genreId} WHERE album_id=#{id}";
    final String DELETE = "DELETE FROM album WHERE album_id=#{id}";

    @Select(GETOBJECT)
    @Results(value = { @Result(property = "id", column = "album_id"), @Result(property = "name", column = "album_name"),
	    @Result(property = "length", column = "album_length"), @Result(property = "artistId", column = "artist_id"),
	    @Result(property = "genreId", column = "genre_id") })
    AlbumAsDB getObjectAsDB(int id);

    @Select(GETLISTOFOBJECTS)
    @Results(value = { @Result(property = "id", column = "album_id"), @Result(property = "name", column = "album_name"),
	    @Result(property = "length", column = "album_length"), @Result(property = "artistId", column = "artist_id"),
	    @Result(property = "genreId", column = "genre_id") })
    List<AlbumAsDB> getObjectAsDBList();

    @Select("SELECT * FROM album_content WHERE album_id=#{id}")
    @Results(value = { @Result(property = "id", column = "song_id"), @Result(property = "name", column = "song_name"),
	    @Result(property = "duration", column = "song_duration"),
	    @Result(property = "genreId", column = "genre_id"), @Result(property = "artistId", column = "artist_id") })
    List<SongAsDB> getSongsFromAlbum();

    @Insert(INSERT)
    void add(AlbumAsDB albumAsDB);

    @Update(UPDATE)
    void update(AlbumAsDB albumAsDB);

    @Delete(DELETE)
    void delete(int id);
}
