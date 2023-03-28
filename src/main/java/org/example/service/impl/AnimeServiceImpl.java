package org.example.service.impl;

import org.example.entities.Anime;
import org.example.repository.AnimeRepository;
import org.example.service.AnimeService;
import org.example.utils.PullConnectionBuilder;

public class AnimeServiceImpl implements AnimeService {
    private final AnimeRepository animeRepository = new AnimeRepository(new PullConnectionBuilder());

    @Override
    public void addAnime(Anime anime) {
        animeRepository.add(anime);
    }
}
