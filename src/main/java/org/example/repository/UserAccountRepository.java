package org.example.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entities.UserAccount;
import org.example.utils.ConnectionBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserAccountRepository {

    private final Connection connection;
    private static final Logger logger = LogManager.getLogger(UserAccountRepository.class);

    public UserAccountRepository(ConnectionBuilder connectionBuilder) {
        connection = connectionBuilder.getConnection();
    }


    public boolean add(UserAccount userAccount) {
        String userQuery = "INSERT INTO user_account(name, login, password, picture_url)"
                + "VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(userQuery);

            preparedStatement.setString(1, userAccount.getName());
            preparedStatement.setString(2, userAccount.getLogin());
            preparedStatement.setString(3, userAccount.getPassword());
            preparedStatement.setString(4, userAccount.getPictureURL());

            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.error("Ошибка добавления пользователя:\n" + e.getMessage());
            return false;
        }
    }


    public List<UserAccount> getAll() {
        List<UserAccount> userAccountList = new ArrayList<>();
        String userQuery = "SELECT id, name, login, password, picture_url" +
                " FROM user_account";
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
            logger.error("Ошибка получения всех пользователей:\n" + e.getMessage());
        }
        return userAccountList;
    }


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
            logger.error("Ошибка получения пользователя по id:\n" + e.getMessage());
        }
        return userAccount;
    }


    public UserAccount findByName(String name) {
        UserAccount userAccount = null;
        String userQuery = "SELECT id, name, login, password, picture_url" +
                " FROM user_account WHERE name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(userQuery);

            preparedStatement.setString(1, name);

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
            logger.error("Ошибка нахождения пользователя по name:\n" + e.getMessage());
        }
        return userAccount;
    }


    public void update(UserAccount userAccount) {
        String userQuery =
                "UPDATE user_account SET name = ?,"
                        + "login = ?,"
                        + "password = ?,"
                        + "picture_url = ? "
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
            logger.error("Ошибка изменения пользователя:\n" + e.getMessage());
        }
    }


    public void remove(UserAccount userAccount) {
        String userQuery = "DELETE FROM user_account WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(userQuery);
            preparedStatement.setLong(1, userAccount.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Ошибка удаления пользователя:\n" + e.getMessage());
        }
    }
}
