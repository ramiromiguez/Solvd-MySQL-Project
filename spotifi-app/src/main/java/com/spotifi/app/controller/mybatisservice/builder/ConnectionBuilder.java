package com.spotifi.app.controller.mybatisservice.builder;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.spotifi.app.model.mybatismapper.ArtistMapper;
import com.spotifi.app.model.mybatismapper.CountryMapper;
import com.spotifi.app.model.mybatismapper.GenreMapper;
import com.spotifi.app.model.mybatismapper.SongMapper;

public class ConnectionBuilder {

    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/spotifi";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "r4m1";

    public SqlSession buildConnection() {
	DataSource dataSource = new PooledDataSource(DRIVER, URL, USERNAME, PASSWORD);

	Environment environment = new Environment("Development", new JdbcTransactionFactory(), dataSource);

	Configuration configuration = new Configuration(environment);
	configuration.addMapper(CountryMapper.class);
	configuration.addMapper(GenreMapper.class);
	configuration.addMapper(ArtistMapper.class);
	configuration.addMapper(SongMapper.class);

	SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
	SqlSessionFactory factory = builder.build(configuration);

	return factory.openSession();
    }
}
