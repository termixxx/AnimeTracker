package org.example.logic;

import org.example.entities.Anime;
import org.example.entities.UserAccount;
import org.example.entities.UserAccountAnime;
import org.example.entities.enums.Condition;
import org.example.repository.AnimeRepository;
import org.example.repository.UserAccountAnimeRepository;
import org.example.repository.UserAccountRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserAccountAnimeRepositoryTest {
    UserAccountAnimeRepository userAccountAnimeRepository = new UserAccountAnimeRepository();
    AnimeRepository animeRepository = new AnimeRepository();
    UserAccountRepository userAccountRepository = new UserAccountRepository();
    UserAccount user = userAccountRepository.findByName("Jastin");

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
        animeRepository.add(saga);
        Anime anime = animeRepository.findByName("Сага о винленде 1 сезон");
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

        userAccountAnimeRepository.add(userAccountAnime);
        UserAccountAnime userAnimeFromDB = userAccountAnimeRepository.getByAnimeIdAndUserId(
                anime.getId(),
                user.getId()
        );


        assertEquals(userAccountAnime.toString(), userAnimeFromDB.toString());
    }

    @Test
    public void updateUserAnime() {
        Anime anime = animeRepository.findByName("Сага о винленде 1 сезон");
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
        userAccountAnimeRepository.add(userAccountAnime);
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
        userAccountAnimeRepository.update(userAccountAnimeUpdate);

        assertEquals(userAccountAnimeUpdate.toString(),
                userAccountAnimeRepository.getByAnimeIdAndUserId(
                        anime.getId(),
                        user.getId()).toString()
        );
    }

    @Test
    public void removeUserAnime() {
        Anime anime = animeRepository.findByName("Сага о винленде 1 сезон");
        UserAccountAnime userAnimeFromDB = userAccountAnimeRepository.getByAnimeIdAndUserId(
                anime.getId(),
                user.getId()
        );
        userAccountAnimeRepository.remove(userAnimeFromDB);
        assertNull(userAccountAnimeRepository.getByAnimeIdAndUserId(
                anime.getId(),
                user.getId()
        ));
    }


    @Test
    public void getAllUserAnime() {

        List<UserAccountAnime> userAccountAnimeList = userAccountAnimeRepository.getAll();

        userAccountAnimeList.stream()
                .map(UserAccountAnime::toString)
                .forEach(System.out::println);
    }
}
