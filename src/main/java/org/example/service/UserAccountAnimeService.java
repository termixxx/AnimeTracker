package org.example.service;

import org.example.dao.UserAccountAnimeDAO;
import org.example.entities.UserAccountAnime;
import org.example.enums.Condition;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.jdbcHelper.ConnectionService.getConnection;

public class UserAccountAnimeService implements UserAccountAnimeDAO {
    Connection connection = getConnection();

    @Override
    public void add(UserAccountAnime userAccountAnime) {
        String userAnimeQuery = "INSERT INTO user_account_anime "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(userAnimeQuery);
            preparedStatement.setInt(1, userAccountAnime.getNumberOfEpisodesViewed());
            preparedStatement.setBoolean(2, userAccountAnime.getFavorite());
            preparedStatement.setString(3, userAccountAnime.getComment());
            preparedStatement.setDate(4, Date.valueOf(userAccountAnime.getDateAdded()));
            preparedStatement.setString(5, userAccountAnime.getConditionEnum().toString());
            preparedStatement.setDouble(6, userAccountAnime.getRating());
            preparedStatement.setLong(7, userAccountAnime.getAnimeId());
            preparedStatement.setLong(8, userAccountAnime.getUserId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UserAccountAnime> getAll() {
        String userAnimeQuery = "SELECT * FROM user_account_anime";
        List<UserAccountAnime> userAccountAnimeList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(userAnimeQuery);
            while (resultSet.next()) {
                UserAccountAnime userAccountAnime = new UserAccountAnime(
                        resultSet.getInt("number_of_episodes_viewed"),
                        resultSet.getBoolean("favorite"),
                        resultSet.getString("comment"),
                        resultSet.getDate("date_added").toLocalDate(),
                        (Condition) resultSet.getObject("condition"),
                        resultSet.getDouble("rating"),
                        resultSet.getLong("anime_id"),
                        resultSet.getLong("user_id")
                );
                userAccountAnimeList.add(userAccountAnime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userAccountAnimeList;
    }

    @Override
    public UserAccountAnime getByAnimeIdAndUserId(Long animeId, Long userId) {
        UserAccountAnime userAccountAnime = null;
        String userAccountQuery = "SELECT * FROM user_account_anime WHERE anime_id = ? AND user_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(userAccountQuery);

            preparedStatement.setLong(1, animeId);
            preparedStatement.setLong(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userAccountAnime = new UserAccountAnime(
                        resultSet.getInt("number_of_episodes_viewed"),
                        resultSet.getBoolean("favorite"),
                        resultSet.getString("comment"),
                        resultSet.getDate("date_added").toLocalDate(),
                        (Condition) resultSet.getObject("condition"),
                        resultSet.getDouble("rating"),
                        resultSet.getLong("anime_id"),
                        resultSet.getLong("user_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userAccountAnime;
    }

    @Override
    public void update(UserAccountAnime userAccountAnime) {
        String userAccountQuery =
                "UPDATE user_account_anime SET number_of_episodes_viewed=?,"
                        + "favorite = ?,"
                        + "comment = ?,"
                        + "date_added = ?,"
                        + "condition = ?,"
                        + "rating = ?"
                        + "WHERE anime_id = ? && user_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(userAccountQuery);

            preparedStatement.setInt(1, userAccountAnime.getNumberOfEpisodesViewed());
            preparedStatement.setBoolean(2, userAccountAnime.getFavorite());
            preparedStatement.setString(3, userAccountAnime.getComment());
            preparedStatement.setDate(4, Date.valueOf(userAccountAnime.getDateAdded()));
            preparedStatement.setString(5, userAccountAnime.getConditionEnum().toString());
            preparedStatement.setDouble(6, userAccountAnime.getRating());
            preparedStatement.setLong(7, userAccountAnime.getAnimeId());
            preparedStatement.setLong(8, userAccountAnime.getUserId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(UserAccountAnime userAccountAnime) {
        String userAccountQuery = "DELETE FROM user_account_anime WHERE anime_id = ? AND user_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(userAccountQuery);
            preparedStatement.setLong(1, userAccountAnime.getAnimeId());
            preparedStatement.setLong(2, userAccountAnime.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
