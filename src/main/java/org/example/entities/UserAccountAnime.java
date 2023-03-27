package org.example.entities;

import org.example.entities.enums.Condition;

import java.time.LocalDate;

public class UserAccountAnime {
    private Integer numberOfEpisodesViewed;
    private Boolean favorite;
    private String comment;
    private final LocalDate dateAdded;
    private Enum<Condition> conditionEnum;
    private Integer rating;
    private final Long animeId;
    private final Long userId;

    public UserAccountAnime(Integer numberOfEpisodesViewed, Boolean favorite, String comment, LocalDate dateAdded, Enum<Condition> conditionEnum, Integer rating, Long animeId, Long userId) {
        this.numberOfEpisodesViewed = numberOfEpisodesViewed;
        this.favorite = favorite;
        this.comment = comment;
        this.dateAdded = dateAdded;
        this.conditionEnum = conditionEnum;
        this.rating = rating;
        this.animeId = animeId;
        this.userId = userId;
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Long getAnimeId() {
        return animeId;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "UserAccountAnime{" +
                "numberOfEpisodesViewed=" + numberOfEpisodesViewed +
                ", favorite=" + favorite +
                ", comment='" + comment + '\'' +
                ", dateAdded=" + dateAdded +
                ", conditionEnum=" + conditionEnum +
                ", rating=" + rating +
                ", animeId=" + animeId +
                ", userId=" + userId +
                '}';
    }
}
