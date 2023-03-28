package org.example.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PullConnectionBuilder implements ConnectionBuilder {

    private static final Logger logger = LogManager.getLogger(PullConnectionBuilder.class);
    private DataSource dataSource;

    public PullConnectionBuilder() {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/AnimeTracker");
        } catch (NamingException e) {
            logger.error("", e);
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            logger.error("", e);
        }
        return connection;
    }
}
