package org.example.logic;


import org.example.entities.Anime;
import org.example.repository.AnimeRepository;
import org.example.service.DirectConnectionBuilder;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AnimeRepositoryTest {
    private final AnimeRepository animeRepository = new AnimeRepository(new DirectConnectionBuilder());

    Anime saga = new Anime(null, "Сага о винленде 1 сезон", 48,
            "Приключение", "Про викингов",
            LocalDate.of(20018, 8, 11), "img");

    @Test
    public void addAndGetByIdAnime() {
        animeRepository.add(saga);
        Anime foundedAnime = animeRepository.findByName(saga.getName());
        assertEquals(saga.toTest(), foundedAnime.toTest());

        animeRepository.remove(animeRepository.findByName(saga.getName()));
    }

    @Test
    public void removeAnime() {
        animeRepository.add(saga);

        Anime sagaFounded = animeRepository.findByName(saga.getName());
        animeRepository.remove(sagaFounded);
        assertNull(animeRepository.findByName(saga.getName()));
    }

    @Test
    public void updateAnime() {
        animeRepository.add(saga);

        Anime newSaga = new Anime(
                animeRepository.findByName(saga.getName()).getId(), "Сага о винленде 2 сезон",
                24, "Фермерство", "Про фермеров",
                LocalDate.of(2020, 1, 12), "img2");

        animeRepository.update(newSaga);
        assertEquals(newSaga.toTest(), animeRepository.findByName(newSaga.getName()).toTest());

        animeRepository.remove(animeRepository.findByName(newSaga.getName()));
    }

    @Test
    public void getAllAnime() {
        List<Anime> animeList = animeRepository.getAll();

        animeList.stream()
                .map(Anime::toTest)
                .forEach(System.out::println);
    }
}
