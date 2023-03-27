package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entities.Anime;
import org.example.repository.AnimeRepository;


public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Начало работы программы");

        AnimeRepository animeRepository = new AnimeRepository();

        animeRepository.getAll().stream()
                .map(Anime::toString)
                .forEach(System.out::println);


        logger.info("Завершение работы программы . . .");
    }
}