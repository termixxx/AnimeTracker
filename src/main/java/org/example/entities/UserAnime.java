package org.example.entities;

import org.example.enums.Condition;

import java.time.LocalDate;

public class UserAnime {
    private final Long id;
    private Integer numberOfEpisodesViewed;
    private Boolean favorite;
    private String comment;
    private final LocalDate dateAdded;
    private Enum<Condition> conditionEnum;
    private Double rating;
    private final Long animeId;
    private final Long userId;

    public UserAnime(Long id, Integer numberOfEpisodesViewed, Boolean favorite, String comment, LocalDate dateAdded, Enum<Condition> conditionEnum, Double rating, Long animeId, Long userId) {
        this.id = id;
        this.numberOfEpisodesViewed = numberOfEpisodesViewed;
        this.favorite = favorite;
        this.comment = comment;
        this.dateAdded = dateAdded;
        this.conditionEnum = conditionEnum;
        this.rating = rating;
        this.animeId = animeId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public Integer getNumberOfEpisodesViewed() {
        return numberOfEpisodesViewed;
    }

    public void setNumberOfEpisodesViewed(Integer numberOfEpisodesViewed) {
        this.numberOfEpisodesViewed = numberOfEpisodesViewed;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public Enum<Condition> getConditionEnum() {
        return conditionEnum;
    }

    public void setConditionEnum(Enum<Condition> conditionEnum) {
        this.conditionEnum = conditionEnum;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getAnimeId() {
        return animeId;
    }

    public Long getUserId() {
        return userId;
    }
}
