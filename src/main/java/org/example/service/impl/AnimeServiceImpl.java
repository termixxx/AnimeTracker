package org.example.service.impl;

import org.example.entities.Anime;
import org.example.repository.AnimeRepository;
import org.example.service.AnimeService;

public class AnimeServiceImpl implements AnimeService {
    private final AnimeRepository animeRepository = new AnimeRepository();

    @Override
    public void addAnime(Anime anime) {
        animeRepository.add(anime);
    }
}
