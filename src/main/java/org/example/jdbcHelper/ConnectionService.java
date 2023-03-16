package org.example.jdbcHelper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionService {
    public static Connection getConnection() {
        var props = new Properties();

        try (InputStream in = Files.newInputStream(Paths.get("src/main/resources/db.properties"))) {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
