package org.example.jdbcHelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionService {
    private static final Logger logger = LogManager.getLogger(ConnectionService.class);

    public Connection getConnection() {
        var props = new Properties();

        logger.info("Подключение к базе данных . . .");
        try (InputStream in = Files.newInputStream(Paths.get("src/main/resources/db.properties"))) {
            props.load(in);
        } catch (IOException e) {
            logger.error("Ошибка загрузки настроек подключения к базе данных:\n" + e.getMessage());
        }

        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            logger.info("Подключение к базе данных прошло успешно");
        } catch (SQLException e) {
            logger.error("Ошибка подключения к базе данных:\n" + e.getMessage());
        }

        return connection;
    }
}
