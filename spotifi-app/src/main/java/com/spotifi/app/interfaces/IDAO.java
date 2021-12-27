package com.spotifi.app.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {
    void add(T t) throws SQLException;
    void update(T t) throws SQLException;
    void delete(int id) throws SQLException;
    public List<T> getObjectList() throws SQLException;
    public T getObject(int id) throws SQLException;
}
