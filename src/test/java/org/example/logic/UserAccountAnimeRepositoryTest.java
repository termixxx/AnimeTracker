package org.example.logic;

import org.example.entities.Anime;
import org.example.entities.UserAccount;
import org.example.entities.UserAccountAnime;
import org.example.entities.enums.Condition;
import org.example.repository.AnimeRepository;
import org.example.repository.UserAccountAnimeRepository;
import org.example.repository.UserAccountRepository;
import org.example.service.DirectConnectionBuilder;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserAccountAnimeRepositoryTest {
    UserAccountAnimeRepository userAccountAnimeRepository = new UserAccountAnimeRepository(new DirectConnectionBuilder());
    AnimeRepository animeRepository = new AnimeRepository(new DirectConnectionBuilder());
    UserAccountRepository userAccountRepository = new UserAccountRepository(new DirectConnectionBuilder());

    Anime saga = new Anime(null, "Сага о винленде 1 сезон", 48,
            "Приключение", "Про викингов",
            LocalDate.of(20018, 8, 11), "img");
    UserAccount user = new UserAccount(null,
            "Jastin", "bak12", "pas", "img");

    @Test
    public void addAndGetByIdAnimeAndIdUser() {
        add();
        long animeId = animeRepository.findByName(saga.getName()).getId();
        long userId = userAccountRepository.findByName(user.getName()).getId();

        UserAccountAnime userAccountAnime = new UserAccountAnime(
                2,
                false,
                "Хорошая рисовка",
                LocalDate.now(),
                Condition.WATCHING,
                8,
                animeId,
                userId
        );

        userAccountAnimeRepository.add(userAccountAnime);
        UserAccountAnime userAnimeFromDB = userAccountAnimeRepository.getByAnimeIdAndUserId(
                animeId,
                userId
        );
        assertEquals(userAccountAnime.toString(), userAnimeFromDB.toString());

        clear(userAnimeFromDB);
    }

    private void add() {
        animeRepository.add(saga);
        userAccountRepository.add(user);
    }

    private void clear(UserAccountAnime userAnimeFromDB) {
        animeRepository.remove(animeRepository.findByName(saga.getName()));
        userAccountRepository.remove(userAccountRepository.findByName(user.getName()));
        userAccountAnimeRepository.remove(userAnimeFromDB);
    }

    @Test
    public void updateUserAnime() {
        add();
        long animeId = animeRepository.findByName(saga.getName()).getId();
        long userId = userAccountRepository.findByName(user.getName()).getId();

        UserAccountAnime userAccountAnime = new UserAccountAnime(
                2,
                false,
                "Хорошая рисовка",
                LocalDate.now(),
                Condition.WATCHING,
                8,
                animeId,
                userId
        );
        userAccountAnimeRepository.add(userAccountAnime);
        UserAccountAnime userAccountAnimeUpdate = new UserAccountAnime(
                20,
                true,
                "Хорошая рисовка",
                LocalDate.now(),
                Condition.WATCHING,
                8,
                animeId,
                userId
        );
        userAccountAnimeRepository.update(userAccountAnimeUpdate);

        assertEquals(userAccountAnimeUpdate.toString(),
                userAccountAnimeRepository.getByAnimeIdAndUserId(
                        animeId,
                        userId
                ).toString());

        clear(userAccountAnimeRepository.getByAnimeIdAndUserId(animeId, userId));
    }

    @Test
    public void removeUserAnime() {
        add();
        long animeId = animeRepository.findByName(saga.getName()).getId();
        long userId = userAccountRepository.findByName(user.getName()).getId();

        UserAccountAnime userAccountAnime = new UserAccountAnime(
                2,
                false,
                "Хорошая рисовка",
                LocalDate.now(),
                Condition.WATCHING,
                8,
                animeId,
                userId
        );
        userAccountAnimeRepository.add(userAccountAnime);

        UserAccountAnime userAnimeFromDB = userAccountAnimeRepository.getByAnimeIdAndUserId(
                animeId,
                userId
        );
        userAccountAnimeRepository.remove(userAnimeFromDB);
        assertNull(userAccountAnimeRepository.getByAnimeIdAndUserId(
                animeId,
                userId
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
