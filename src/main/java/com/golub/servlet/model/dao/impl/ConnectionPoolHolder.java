package com.golub.servlet.model.dao.impl;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionPoolHolder {

    private static final String PROPERTIES_FILE_NAME = "db.properties";
    private static final String CONNECTION_DRIVER = "db.connection.driver";
    private static final String CONNECTION_URL = "db.connection.url";
    private static final String CONNECTION_USERNAME = "db.connection.username";
    private static final String CONNECTION_PASSWORD = "db.connection.password";
    private static final int MIN_IDLE = 5;
    private static final int MAX_IDLE = 10;
    private static final int MAX_OPEN_PREPARED_STATEMENTS = 100;

    private static volatile DataSource dataSource;
    private static final Logger logger = LogManager.getLogger(ConnectionPoolHolder.class);

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    Properties properties = new Properties();
                    String propFileName = PROPERTIES_FILE_NAME;

                    try(InputStream inputStream = Thread.currentThread()
                            .getContextClassLoader()
                            .getResourceAsStream(propFileName)) {

                        if (inputStream != null) {
                            properties.load(inputStream);
                        } else {
                            logger.fatal("Unable to open file:" + propFileName );
                            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
                        }

                        Class.forName(properties.getProperty(CONNECTION_DRIVER));

                        BasicDataSource ds = new BasicDataSource();
                        ds.setUrl(properties.getProperty(CONNECTION_URL));
                        ds.setUsername(properties.getProperty(CONNECTION_USERNAME));
                        ds.setPassword(properties.getProperty(CONNECTION_PASSWORD));

                        ds.setMinIdle(MIN_IDLE);
                        ds.setMaxIdle(MAX_IDLE);
                        ds.setMaxOpenPreparedStatements(MAX_OPEN_PREPARED_STATEMENTS);

                        dataSource = ds;

                    } catch (IOException | ClassNotFoundException e) {
                        logger.fatal("Caught exception:", e);
                        e.printStackTrace();
                    }
                }
            }
        }

        return dataSource;
    }

}
