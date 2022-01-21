package com.spotifi.app.model.mybatismapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.spotifi.app.model.entities.ArtistAsDB;

public interface ArtistMapper {
    final String GETOBJECT = "SELECT * FROM artist WHERE artist_id=#{id}";
    final String GETLISTOFOBJECTS = "SELECT * FROM artist";
    final String INSERT = "INSERT INTO artist(artist_name, country_id, genre_id) VALUES (#{name}, #{countryId}, #{genreId})";
    final String UPDATE = "UPDATE artist SET artist_name=#{name}, country_id=#{countryId}, genre_id=#{genreId} WHERE artist_id=#{id}";
    final String DELETE = "DELETE FROM artist WHERE artist_id=#{id}";

    @Select(GETOBJECT)
    @Results(value = { @Result(property = "id", column = "artist_id"),
	    @Result(property = "name", column = "artist_name"), @Result(property = "countryId", column = "country_id"),
	    @Result(property = "genreId", column = "genre_id") })
    ArtistAsDB getObjectAsDB(int id);

    @Select(GETLISTOFOBJECTS)
    @Results(value = { @Result(property = "id", column = "artist_id"),
	    @Result(property = "name", column = "artist_name"), @Result(property = "countryId", column = "country_id"),
	    @Result(property = "genreId", column = "genre_id") })
    List<ArtistAsDB> getObjectAsDBList();

    @Insert(INSERT)
    void add(ArtistAsDB artist);

    @Update(UPDATE)
    void update(ArtistAsDB artist);

    @Delete(DELETE)
    void delete(int id);
}
