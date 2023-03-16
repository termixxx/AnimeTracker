package org.example.dao;

import org.example.entities.UserAnime;

import java.util.List;

public interface UserAnimeDAO {
    // create
    void add(UserAnime userAnime);

    // read
    List<UserAnime> getAll();

    UserAnime getByAnimeIdAndUserId(Long animeId, Long userId);

    // update
    void update(UserAnime userAnime);

    // delete
    void remove(UserAnime userAnime);

}
