package org.example.logic;

import org.example.entities.Anime;
import org.example.entities.UserAccount;
import org.example.entities.UserAccountAnime;
import org.example.enums.Condition;
import org.example.service.AnimeService;
import org.example.service.UserAccountAnimeService;
import org.example.service.UserAccountService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserAccountAnimeServiceTest {
    UserAccountAnimeService userAccountAnimeService = new UserAccountAnimeService();
    AnimeService animeService = new AnimeService();
    UserAccountService userAccountService = new UserAccountService();
    UserAccount user = userAccountService.findByName("Jastin");

    @Test
    public void addAndGetByIdAnimeAndIdUser() {
        Anime saga = new Anime(
                null,
                "Сага о винленде 1 сезон",
                48,
                "Приключение",
                "Про викингов",
                LocalDate.of(20018, 8, 11),
                "img");
        animeService.add(saga);
        Anime anime = animeService.findByName("Сага о винленде 1 сезон");
        UserAccountAnime userAccountAnime = new UserAccountAnime(
                2,
                false,
                "Хорошая рисовка",
                LocalDate.now(),
                Condition.WATCHING,
                8,
                anime.getId(),
                user.getId()
        );

        userAccountAnimeService.add(userAccountAnime);
        UserAccountAnime userAnimeFromDB = userAccountAnimeService.getByAnimeIdAndUserId(
                anime.getId(),
                user.getId()
        );


        assertEquals(userAccountAnime.toString(), userAnimeFromDB.toString());
    }

    @Test
    public void updateUserAnime() {
        Anime anime = animeService.findByName("Сага о винленде 1 сезон");
        UserAccountAnime userAccountAnime = new UserAccountAnime(
                2,
                false,
                "Хорошая рисовка",
                LocalDate.now(),
                Condition.WATCHING,
                8,
                anime.getId(),
                user.getId()
        );
        userAccountAnimeService.add(userAccountAnime);
        UserAccountAnime userAccountAnimeUpdate = new UserAccountAnime(
                20,
                true,
                "Хорошая рисовка",
                LocalDate.now(),
                Condition.WATCHING,
                8,
                anime.getId(),
                user.getId()
        );
        userAccountAnimeService.update(userAccountAnimeUpdate);

        assertEquals(userAccountAnimeUpdate.toString(),
                userAccountAnimeService.getByAnimeIdAndUserId(
                        anime.getId(),
                        user.getId()).toString()
        );
    }

    @Test
    public void removeUserAnime() {
        Anime anime = animeService.findByName("Сага о винленде 1 сезон");
        UserAccountAnime userAnimeFromDB = userAccountAnimeService.getByAnimeIdAndUserId(
                anime.getId(),
                user.getId()
        );
        userAccountAnimeService.remove(userAnimeFromDB);
        assertNull(userAccountAnimeService.getByAnimeIdAndUserId(
                anime.getId(),
                user.getId()
        ));
    }


    @Test
    public void getAllUserAnime() {

        List<UserAccountAnime> userAccountAnimeList = userAccountAnimeService.getAll();

        userAccountAnimeList.stream()
                .map(UserAccountAnime::toString)
                .forEach(System.out::println);
    }
}
