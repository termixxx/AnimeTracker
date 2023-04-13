package org.example.logic;


import org.example.entities.Anime;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.example.resourses.Data.anime;
import static org.example.resourses.Data.animeRepository;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AnimeRepositoryTest {


    @Test
    public void addAndGetByIdAnime() {
        animeRepository.add(anime);
        Anime foundedAnime = animeRepository.findByName(anime.getName());
        assertEquals(anime.toTest(), foundedAnime.toTest());

        animeRepository.remove(animeRepository.findByName(anime.getName()));
    }

    @Test
    public void removeAnime() {
        animeRepository.add(anime);

        Anime sagaFounded = animeRepository.findByName(anime.getName());
        animeRepository.remove(sagaFounded);
        assertNull(animeRepository.findByName(anime.getName()));
    }

    @Test
    public void updateAnime() {
        animeRepository.add(anime);

        Anime newSaga = new Anime(
                animeRepository.findByName(anime.getName()).getId(), "Сага о винленде 2 сезон",
                24, "Фермерство", "Про фермеров",
                LocalDate.of(2020, 1, 12), "img2");

        animeRepository.update(newSaga);
        assertEquals(newSaga.toTest(), animeRepository.findByName(newSaga.getName()).toTest());

        animeRepository.remove(animeRepository.findByName(newSaga.getName()));
    }

    @Test
    public void getAllAnime() {
        List<Anime> animeList = animeRepository.getAll();
        assertEquals(12, animeList.size());
    }
}
