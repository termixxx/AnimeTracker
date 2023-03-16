package org.example;

import org.example.entities.Anime;
import org.example.service.AnimeService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnimeService animeService = new AnimeService();
        List<Anime> animeList = animeService.getAll();
        animeList.stream().map(Anime::toString).forEach(System.out::println);
    }
}