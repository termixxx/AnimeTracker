package org.example.service.impl;

import org.example.entities.UserAccountAnime;
import org.example.repository.UserAccountAnimeRepository;
import org.example.service.UserAccountAnimeService;
import org.example.utils.PullConnectionBuilder;

import java.util.List;

public class UserAccountAnimeServiceImpl implements UserAccountAnimeService {
    UserAccountAnimeRepository userAnimeRepository = new UserAccountAnimeRepository(new PullConnectionBuilder());

    @Override
    public List<UserAccountAnime> findAllAnimeForUserId(Long id) {
        return userAnimeRepository.getByUserId(id);
    }

    @Override
    public void save(UserAccountAnime accountAnime) {
        userAnimeRepository.add(accountAnime);
    }
}
