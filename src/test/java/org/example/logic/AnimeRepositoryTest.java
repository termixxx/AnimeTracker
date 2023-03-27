package org.example.logic;


import org.example.entities.Anime;
import org.example.repository.AnimeRepository;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AnimeRepositoryTest {
    private final AnimeRepository animeRepository = new AnimeRepository();

    @Test
    public void addAndGetByIdAnime() {

        Anime saga = new Anime(
                null,
                "Сага о винленде 1 сезон",
                48,
                "Приключение",
                "Про викингов",
                LocalDate.of(20018, 8, 11),
                "img");


        animeRepository.add(saga);
        Anime foundedAnime = animeRepository.findByName(saga.getName());


        assertEquals(saga.toTest(), foundedAnime.toTest());
    }

    @Test
    public void removeAnime() {
        Anime saga = animeRepository.findByName("Сага о винленде 2 сезон");
        animeRepository.remove(saga);
        assertNull(animeRepository.findByName(saga.getName()));
    }

    @Test
    public void updateAnime() {
        Anime saga = new Anime(
                null,
                "Сага о винленде 1 сезон",
                48,
                "Приключение",
                "Про викингов",
                LocalDate.of(20018, 8, 11),
                "img");
        animeRepository.add(saga);
        Anime newSaga = new Anime(
                animeRepository.findByName(saga.getName()).getId(),
                "Сага о винленде 2 сезон",
                24,
                "Фермерство",
                "Про фермеров",
                LocalDate.of(2020, 1, 12),
                "img2");
        animeRepository.update(newSaga);
        assertEquals(newSaga.toTest(), animeRepository.findByName(newSaga.getName()).toTest());
    }

    @Test
    public void getAllAnime() {

        List<Anime> animeList = animeRepository.getAll();

        animeList.stream()
                .map(Anime::toTest)
                .forEach(System.out::println);
    }
}
