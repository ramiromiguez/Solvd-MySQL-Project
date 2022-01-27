package com.spotifi.app.model.mybatismapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.spotifi.app.model.entities.Country;

public interface CountryMapper {
    final String GETOBJECT = "SELECT * FROM country WHERE country_id=#{id}";
    final String GETLISTOFOBJECTS = "SELECT * FROM country";
    final String INSERT = "INSERT INTO country(country_name) VALUES (#{name})";
    final String UPDATE = "UPDATE country SET country_name=#{name} WHERE country_id=#{id}";
    final String DELETE = "DELETE FROM country WHERE country_id=#{id}";

    @Select(GETOBJECT)
    @Results(value = { @Result(property = "id", column = "country_id"),
	    @Result(property = "name", column = "country_id") })
    Country getObject(int id);

    @Select(GETLISTOFOBJECTS)
    @Results(value = { @Result(property = "id", column = "country_id"),
	    @Result(property = "name", column = "country_name") })
    List<Country> getObjectList();

    @Insert(INSERT)
    void add(Country country);

    @Update(UPDATE)
    void update(Country country);

    @Delete(DELETE)
    void delete(int id);
}
