package org.example.dao;

import org.example.entities.Anime;

import java.util.List;

public interface AnimeDAO {
    // create
    void add(Anime anime);

    // read
    List<Anime> getAll();

    Anime getById(Long id);

    // update
    void update(Anime anime);

    // delete
    void remove(Anime anime);
}
