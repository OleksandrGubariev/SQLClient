package com.sqlclient.config;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class PropertyReader {
    private Properties properties = new Properties();
    private String path;

    public PropertyReader(String path) {
        this.path = path;
    }

    public Properties readProperties() {
        if("PROD".equalsIgnoreCase(System.getenv("ENV"))){
            return readProdProperties();
        }
        return readDevProperties();
    }

    private Properties readDevProperties(){
        try (InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(path)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("No properties on path " + path);
            }
            properties.load(inputStream);
            log.debug("Read properties from path: {}", path);
            return properties;
        } catch (IOException e) {
            log.error("Can't read properties file: {} ", path, e);
            throw new RuntimeException("Can't read properties file " + path, e);
        }
    }
    private Properties readProdProperties(){
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        properties.setProperty("jdbc.url", dbUrl);

        String port = System.getenv("PORT");
        properties.setProperty("port", port);

        return properties;
    }
}