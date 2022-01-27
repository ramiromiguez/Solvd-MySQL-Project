package com.spotifi.app.model.mybatismapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.spotifi.app.model.entities.Genre;

public interface GenreMapper {
    final String GETOBJECT = "SELECT * FROM genre WHERE genre_id=#{id}";
    final String GETLISTOFOBJECTS = "SELECT * FROM genre";
    final String INSERT = "INSERT INTO genre(genre_name) VALUES(#{name})";
    final String UPDATE = "UPDATE genre SET genre_name=#{name} WHERE genre_id=#{id}";
    final String DELETE = "DELETE FROM genre WHERE genre_id=#{id}";

    @Select(GETOBJECT)
    @Results(value = { @Result(property = "id", column = "genre_id"),
	    @Result(property = "name", column = "genre_name") })
    Genre getObject(int id);

    @Select(GETLISTOFOBJECTS)
    @Results(value = { @Result(property = "id", column = "genre_id"),
	    @Result(property = "name", column = "genre_name") })
    List<Genre> getObjectList();

    @Insert(INSERT)
    void add(Genre genre);

    @Update(UPDATE)
    void update(Genre genre);

    @Delete(DELETE)
    void delete(int id);
}
