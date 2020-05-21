package com.sqlclient.config;

import lombok.RequiredArgsConstructor;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.util.Properties;

@RequiredArgsConstructor
public class DataSourceFactory {
    private final Properties properties;

    public DataSource getDataSource() {
        String dbUrl = properties.getProperty("jdbc.url");
        String user = properties.getProperty("jdbc.user");

        PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();
        if (user != null) {
            String password = properties.getProperty("jdbc.password");

            pgSimpleDataSource.setUser(user);
            pgSimpleDataSource.setPassword(password);
        }
        pgSimpleDataSource.setUrl(dbUrl);

        return pgSimpleDataSource;
    }
}
