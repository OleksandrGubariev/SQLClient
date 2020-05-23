package com.sqlclient.config;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class PropertyReader {
    private static final String DEVELOP_APPLICATION_PROPERTIES = "dev.application.properties";
    private final String path;
    private final Properties properties;

    public PropertyReader(String path) {
        this.path = path;
        properties = readProperties();
    }

    public Properties getProperties() {
        return new Properties(properties);
    }

    private Properties readProperties() {
        log.info("Try get properties");
        String prodEnvironment = System.getenv("ENV");
        if (prodEnvironment != null && prodEnvironment.equalsIgnoreCase("PROD")) {
            log.info("Type of properties is production");
            return getProdProperties();
        }
        log.info("Type of properties is development");
        return getDevProperties();
    }

    private Properties getProdProperties() {
        try {
            String dbUrl = System.getenv("DATABASE_URL");
            properties.setProperty("jdbc.url", dbUrl);

            String port = System.getenv("PORT");
            properties.setProperty("port", port);

            String host = System.getenv("HOST");
            properties.setProperty("host", host);
            log.debug("Read prod properties");

            return properties;
        } catch (Exception e) {
            log.error("Error while were trying to get connection properties on production environment", e);
            throw new RuntimeException("Exception while were trying to get connection properties on production environment", e);
        }
    }

    private Properties getDevProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(path);
             InputStream devPropertiesStream = PropertyReader.class.getClassLoader().getResourceAsStream(DEVELOP_APPLICATION_PROPERTIES)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("No properties on path " + path);
            }
            properties.load(inputStream);
            log.debug("Read properties from path: {}", path);

            if (devPropertiesStream != null) {
                properties.load(devPropertiesStream);
                log.debug("Read properties from path: {}", DEVELOP_APPLICATION_PROPERTIES);
            }
            return properties;
        } catch (IOException e) {
            log.error("Can't read properties file: {} ", path, e);
            throw new RuntimeException("Can't read properties file " + path, e);
        }

    }
}
