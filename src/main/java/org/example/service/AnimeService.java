package org.example.service;

import org.example.entities.Anime;

import java.util.List;

public interface AnimeService {
    void addAnime(Anime anime);

    Anime getAnimeById(Long id);

    Anime findByName(String name);

    List<Anime> getAll();
}
