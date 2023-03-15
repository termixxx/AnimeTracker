package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.example.jdbcService.ConnectionService.getConnection;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM anime")) {
                while (resultSet.next()) {
                    for (int i = 1; i < 7; i++) {
                        System.out.print(resultSet.getString(i) + '\t');
                    }
                }
            }
        }
    }


}