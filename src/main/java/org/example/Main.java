package org.example;

import org.example.entities.Anime;
import org.example.service.AnimeService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        AnimeService animeService = new AnimeService();
        animeService.add(new Anime(
                null,
                "сага",
                15,
                "trawel",
                "opisane",
                LocalDate.of(2200, 10, 11),
                "123"));
        System.out.printf(animeService.getById(1L).toString());
    }
}