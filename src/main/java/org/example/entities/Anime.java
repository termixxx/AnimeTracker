package org.example.entities;

import java.time.LocalDate;

public class Anime {
    private final Long id;
    private String name;
    private Integer countOfSeries;
    private String genres;
    private String description;
    private final LocalDate releaseYear;
    private String pictureURL;

    public Anime(Long id, String name, Integer countOfSeries, String genres, String description, LocalDate releaseYear, String pictureURL) {
        this.id = id;
        this.name = name;
        this.countOfSeries = countOfSeries;
        this.genres = genres;
        this.description = description;
        this.releaseYear = releaseYear;
        this.pictureURL = pictureURL;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCountOfSeries() {
        return countOfSeries;
    }

    public void setCountOfSeries(Integer countOfSeries) {
        this.countOfSeries = countOfSeries;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseYear() {
        return releaseYear;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    @Override
    public String toString() {
        return "Anime{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countOfSeries=" + countOfSeries +
                ", genres='" + genres + '\'' +
                ", description='" + description + '\'' +
                ", releaseYear=" + releaseYear +
                ", pictureURL='" + pictureURL + '\'' +
                '}';
    }
}
