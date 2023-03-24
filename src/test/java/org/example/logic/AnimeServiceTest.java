package org.example.logic;


import org.example.entities.Anime;
import org.example.service.AnimeService;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AnimeServiceTest {
    private final AnimeService animeService = new AnimeService();

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


        animeService.add(saga);
        Anime foundedAnime = animeService.findByName(saga.getName());


        assertEquals(saga.toTest(), foundedAnime.toTest());
    }

    @Test
    public void removeAnime() {
        Anime saga = animeService.findByName("Сага о винленде 2 сезон");
        animeService.remove(saga);
        assertNull(animeService.findByName(saga.getName()));
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
        animeService.add(saga);
        Anime newSaga = new Anime(
                animeService.findByName(saga.getName()).getId(),
                "Сага о винленде 2 сезон",
                24,
                "Фермерство",
                "Про фермеров",
                LocalDate.of(2020, 1, 12),
                "img2");
        animeService.update(newSaga);
        assertEquals(newSaga.toTest(), animeService.findByName(newSaga.getName()).toTest());
    }

    @Test
    public void getAllAnime() {

        List<Anime> animeList = animeService.getAll();

        animeList.stream()
                .map(Anime::toTest)
                .forEach(System.out::println);
    }
}
