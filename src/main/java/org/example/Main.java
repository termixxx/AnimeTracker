package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entities.Anime;
import org.example.service.AnimeService;

import java.time.LocalDate;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Начало работы программы");

        AnimeService animeService = new AnimeService();
        animeService.add(new Anime(
                null,
                "сага",
                15,
                "trawel",
                "opisanie",
                LocalDate.of(2200, 10, 11),
                "123"));
        animeService.getAll().stream().map(Anime::toString).forEach(System.out::println);


        logger.info("Завершение работы программы . . .");
    }
}