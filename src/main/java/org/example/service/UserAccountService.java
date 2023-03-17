package org.example.service;

import org.example.dao.UserAccountDAO;
import org.example.entities.UserAccount;
import org.example.jdbcHelper.ConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserAccountService implements UserAccountDAO {
    ConnectionService connectionService = new ConnectionService();
    Connection connection = connectionService.getConnection();

    @Override
    public void add(UserAccount userAccount) {
        String userQuery = "INSERT INTO user_account(name, login, password, picture_url)"
                + "VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(userQuery);

            preparedStatement.setString(1, userAccount.getName());
            preparedStatement.setString(2, userAccount.getLogin());
            preparedStatement.setString(3, userAccount.getPassword());
            preparedStatement.setString(4, userAccount.getPictureURL());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UserAccount> getAll() {
        List<UserAccount> userAccountList = new ArrayList<>();
        String userQuery = "SELECT id, name, login, password, picture_url" +
                " FROM user_account WHERE id = ?";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(userQuery);
            while (resultSet.next()) {
                UserAccount userAccount = new UserAccount(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("picture_url")
                );

                userAccountList.add(userAccount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userAccountList;
    }

    @Override
    public UserAccount getById(Long id) {
        UserAccount userAccount = null;
        String userQuery = "SELECT id, name, login, password, picture_url" +
                " FROM user_account WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(userQuery);

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userAccount = new UserAccount(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("picture_url"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userAccount;
    }

    @Override
    public void update(UserAccount userAccount) {
        String userQuery =
                "UPDATE user_account SET name = ?,"
                        + "login = ?,"
                        + "password = ?,"
                        + "picture_url = ?"
                        + "WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(userQuery);

            preparedStatement.setString(1, userAccount.getName());
            preparedStatement.setString(2, userAccount.getLogin());
            preparedStatement.setString(3, userAccount.getPassword());
            preparedStatement.setString(4, userAccount.getPictureURL());
            preparedStatement.setLong(5, userAccount.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(UserAccount userAccount) {
        String userQuery = "DELETE FROM user_account WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(userQuery);
            preparedStatement.setLong(1, userAccount.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
