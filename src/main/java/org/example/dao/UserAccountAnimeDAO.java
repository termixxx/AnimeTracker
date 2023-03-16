package org.example.dao;

import org.example.entities.UserAccountAnime;

import java.util.List;

public interface UserAccountAnimeDAO {
    // create
    void add(UserAccountAnime userAccountAnime);

    // read
    List<UserAccountAnime> getAll();

    UserAccountAnime getByAnimeIdAndUserId(Long animeId, Long userId);

    // update
    void update(UserAccountAnime userAccountAnime);

    // delete
    void remove(UserAccountAnime userAccountAnime);

}
