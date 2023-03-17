package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dao.AnimeDAO;
import org.example.entities.Anime;
import org.example.jdbcHelper.ConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AnimeService implements AnimeDAO {
    ConnectionService connectionService = new ConnectionService();
    Connection connection = connectionService.getConnection();
    private static final Logger logger = LogManager.getLogger(AnimeService.class);

    @Override
    public void add(Anime anime) {
        String animeQuery =
                "INSERT INTO anime (name, count_of_series, genres, description, release_year, picture_url)"
                        + " VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(animeQuery);

            preparedStatement.setString(1, anime.getName());
            preparedStatement.setInt(2, anime.getCountOfSeries());
            preparedStatement.setString(3, anime.getGenres());
            preparedStatement.setString(4, anime.getDescription());
            preparedStatement.setDate(5, Date.valueOf(anime.getReleaseYear()));
            preparedStatement.setString(6, anime.getPictureURL());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Ошибка получения добавления аниме:\n" + e.getMessage());
        }
    }

    @Override
    public List<Anime> getAll() {
        List<Anime> animeList = new ArrayList<>();
        String animeQuery = "SELECT id, name, count_of_series, " +
                "genres, description, release_year, picture_url " +
                "FROM anime";
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(animeQuery);

            while (resultSet.next()) {
                Anime anime = new Anime(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("count_of_series"),
                        resultSet.getString("genres"),
                        resultSet.getString("description"),
                        resultSet.getDate("release_year").toLocalDate(),
                        resultSet.getString("picture_url")
                );

                animeList.add(anime);
            }
        } catch (SQLException e) {
            logger.error("Ошибка получения списка аниме:\n" + e.getMessage());
        }
        return animeList;
    }

    @Override
    public Anime getById(Long id) {
        Anime anime = null;
        String animeQuery = "SELECT id, name, count_of_series, " +
                "genres, description, release_year, picture_url " +
                "FROM anime WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(animeQuery);

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                anime = new Anime(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("count_of_series"),
                        resultSet.getString("genres"),
                        resultSet.getString("description"),
                        resultSet.getDate("release_year").toLocalDate(),
                        resultSet.getString("picture_url")
                );
            }
        } catch (SQLException e) {
            logger.error("Ошибка получения аниме по id:\n" + e.getMessage());
        }
        return anime;
    }

    @Override
    public void update(Anime anime) {
        String animeQuery =
                "UPDATE anime SET name = ?,"
                        + "count_of_series = ?,"
                        + "genres = ?,"
                        + "description = ?,"
                        + "release_year = ?,"
                        + "picture_url = ?"
                        + "WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(animeQuery);

            preparedStatement.setString(1, anime.getName());
            preparedStatement.setInt(2, anime.getCountOfSeries());
            preparedStatement.setString(3, anime.getGenres());
            preparedStatement.setString(4, anime.getDescription());
            preparedStatement.setDate(5, Date.valueOf(anime.getReleaseYear()));
            preparedStatement.setString(6, anime.getPictureURL());
            preparedStatement.setLong(7, anime.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Ошибка обновления аниме:\n" + e.getMessage());
        }
    }

    @Override
    public void remove(Anime anime) {
        String animeQuery = "DELETE FROM anime WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(animeQuery);
            preparedStatement.setLong(1, anime.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Ошибка удаления аниме:\n" + e.getMessage());
        }
    }
}
