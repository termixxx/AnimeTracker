package org.example.service;

import org.example.entities.UserAccountAnime;

import java.util.List;

public interface UserAccountAnimeService {
    List<UserAccountAnime> findAllAnimeForUserId(Long id);

    void save(UserAccountAnime accountAnime);
}
