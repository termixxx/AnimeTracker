package org.example.resourses;

import org.example.entities.Anime;
import org.example.entities.UserAccount;
import org.example.repository.AnimeRepository;
import org.example.repository.UserAccountAnimeRepository;
import org.example.repository.UserAccountRepository;
import org.example.service.DirectConnectionBuilder;

import java.time.LocalDate;

public class Data {

    public final static UserAccount userAccount = new UserAccount(null,
            "Jastin", "bak12", "pas", "img");

    public final static Anime anime = new Anime(null, "Сага о винленде 1 сезон", 48,
            "Приключение", "Про викингов",
            LocalDate.of(20018, 8, 11), "img");
    public final static UserAccount user = new UserAccount(null,
            "Jastin", "bak12", "pas", "img");

    public final static UserAccountAnimeRepository userAccountAnimeRepository = new UserAccountAnimeRepository(new DirectConnectionBuilder());
    public final static AnimeRepository animeRepository = new AnimeRepository(new DirectConnectionBuilder());
    public final static UserAccountRepository userAccountRepository = new UserAccountRepository(new DirectConnectionBuilder());


}
