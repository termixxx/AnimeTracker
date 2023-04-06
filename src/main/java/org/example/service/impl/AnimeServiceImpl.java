package org.example.service.impl;

import org.example.entities.Anime;
import org.example.repository.AnimeRepository;
import org.example.service.AnimeService;
import org.example.utils.PullConnectionBuilder;

import java.util.List;

public class AnimeServiceImpl implements AnimeService {
    private final AnimeRepository animeRepository = new AnimeRepository(new PullConnectionBuilder());

    @Override
    public void addAnime(Anime anime) {
        animeRepository.add(anime);
    }

    @Override
    public List<Anime> getAll() {
        return animeRepository.getAll();
    }

    @Override
    public Anime getAnimeById(Long id) {
        return animeRepository.getById(id);
    }

    @Override
    public Anime findByName(String name) {
        return animeRepository.findByName(name);
    }
}
