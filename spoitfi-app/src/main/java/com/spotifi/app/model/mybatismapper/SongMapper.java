package com.spotifi.app.model.mybatismapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.spotifi.app.model.entities.SongAsDB;

public interface SongMapper {
    final String GETOBJECT = "SELECT * FROM song WHERE song_id=#{id}";
    final String GETLISTOFOBJECTS = "SELECT * FROM song";
    final String INSERT = "INSERT INTO song(song_name, song_duration, genre_id, artist_id) VALUES (#{name}, #{duration}, #{genreId}, #{artistId})";
    final String UPDATE = "UPDATE song SET song_name=#{name}, song_duration=#{duration}, genre_id=#{genreId}, artist_id=#{artistId} WHERE song_id=#{id}";
    final String DELETE = "DELETE FROM song WHERE song_id=#{id}";

    @Select(GETOBJECT)
    @Results(value = { @Result(property = "id", column = "song_id"), @Result(property = "name", column = "song_name"),
	    @Result(property = "duration", column = "song_duration"),
	    @Result(property = "genreId", column = "genre_id"), @Result(property = "artistId", column = "artist_id") })
    SongAsDB getObjectAsDB(int id);

    @Select(GETLISTOFOBJECTS)
    @Results(value = { @Result(property = "id", column = "song_id"), @Result(property = "name", column = "song_name"),
	    @Result(property = "duration", column = "song_duration"),
	    @Result(property = "genreId", column = "genre_id"), @Result(property = "artistId", column = "artist_id") })
    List<SongAsDB> getObjectAsDBList();

    @Select("SELECT * FROM song WHERE artist_id =#{id}")
    @Results(value = { @Result(property = "id", column = "song_id"), @Result(property = "name", column = "song_name"),
	    @Result(property = "duration", column = "song_duration"),
	    @Result(property = "genreId", column = "genre_id"), @Result(property = "artistId", column = "artist_id") })
    List<SongAsDB> getObjectAsDBListWithArtistId(int id);

    @Insert(INSERT)
    void add(SongAsDB artist);

    @Update(UPDATE)
    void update(SongAsDB artist);

    @Delete(DELETE)
    void delete(int id);
}
