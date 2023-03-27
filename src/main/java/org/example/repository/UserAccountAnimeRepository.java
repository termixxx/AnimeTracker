package org.example.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entities.UserAccountAnime;
import org.example.entities.enums.Condition;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserAccountAnimeRepository {
    ConnectionFactory connectionFactory = new ConnectionFactory();
    Connection connection = connectionFactory.getConnection();
    private static final Logger logger = LogManager.getLogger(UserAccountAnimeRepository.class);


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
            preparedStatement.setInt(6, userAccountAnime.getRating());
            preparedStatement.setLong(7, userAccountAnime.getAnimeId());
            preparedStatement.setLong(8, userAccountAnime.getUserId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Ошибка добавления аниме для пользователя:\n" + e.getMessage());
        }
    }


    public List<UserAccountAnime> getAll() {
        String userAccountAnimeQuery = "SELECT number_of_episodes_viewed, favorite, " +
                "comment, date_added, condition, rating, " +
                "anime_id, user_account_id " +
                "FROM user_account_anime";
        List<UserAccountAnime> userAccountAnimeList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(userAccountAnimeQuery);
            while (resultSet.next()) {
                UserAccountAnime userAccountAnime = new UserAccountAnime(
                        resultSet.getInt("number_of_episodes_viewed"),
                        resultSet.getBoolean("favorite"),
                        resultSet.getString("comment"),
                        resultSet.getDate("date_added").toLocalDate(),
                        Condition.valueOf(resultSet.getString("condition")),
                        resultSet.getInt("rating"),
                        resultSet.getLong("anime_id"),
                        resultSet.getLong("user_account_id")
                );
                userAccountAnimeList.add(userAccountAnime);
            }
        } catch (SQLException e) {
            logger.error("Ошибка получения всех аниме для пользователя:\n" + e.getMessage());
        }
        return userAccountAnimeList;
    }


    public UserAccountAnime getByAnimeIdAndUserId(Long animeId, Long userId) {
        UserAccountAnime userAccountAnime = null;
        String userAccountAnimeQuery = "SELECT number_of_episodes_viewed, favorite, " +
                "comment, date_added, condition, rating, " +
                "anime_id, user_account_id " +
                "FROM user_account_anime " +
                "WHERE anime_id = ? AND user_account_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(userAccountAnimeQuery);

            preparedStatement.setLong(1, animeId);
            preparedStatement.setLong(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userAccountAnime = new UserAccountAnime(
                        resultSet.getInt("number_of_episodes_viewed"),
                        resultSet.getBoolean("favorite"),
                        resultSet.getString("comment"),
                        resultSet.getDate("date_added").toLocalDate(),
                        Condition.valueOf(resultSet.getString("condition")),
                        resultSet.getInt("rating"),
                        resultSet.getLong("anime_id"),
                        resultSet.getLong("user_account_id")
                );
            }
        } catch (SQLException e) {
            logger.error("Ошибка получения аниме по animeId для пользователя по userId:\n" + e.getMessage());
        }
        return userAccountAnime;
    }


    public void update(UserAccountAnime userAccountAnime) {
        String userAccountQuery =
                "UPDATE user_account_anime SET number_of_episodes_viewed = ?,"
                        + "favorite = ?,"
                        + "comment = ?,"
                        + "date_added = ?,"
                        + "condition = ?,"
                        + "rating = ? "
                        + "WHERE anime_id = ? AND user_account_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(userAccountQuery);

            preparedStatement.setInt(1, userAccountAnime.getNumberOfEpisodesViewed());
            preparedStatement.setBoolean(2, userAccountAnime.getFavorite());
            preparedStatement.setString(3, userAccountAnime.getComment());
            preparedStatement.setDate(4, Date.valueOf(userAccountAnime.getDateAdded()));
            preparedStatement.setString(5, userAccountAnime.getConditionEnum().toString());
            preparedStatement.setInt(6, userAccountAnime.getRating());
            preparedStatement.setLong(7, userAccountAnime.getAnimeId());
            preparedStatement.setLong(8, userAccountAnime.getUserId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Ошибка обновления аниме для пользователя:\n" + e.getMessage());
        }
    }


    public void remove(UserAccountAnime userAccountAnime) {
        String userAccountQuery = "DELETE FROM user_account_anime " +
                "WHERE anime_id = ? AND user_account_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(userAccountQuery);
            preparedStatement.setLong(1, userAccountAnime.getAnimeId());
            preparedStatement.setLong(2, userAccountAnime.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Ошибка удаления аниме для пользователя:\n" + e.getMessage());
        }
    }
}
