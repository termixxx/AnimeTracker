package org.example.logic;

import org.example.entities.UserAccountAnime;
import org.example.entities.enums.Condition;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.time.LocalDate;
import java.util.List;

import static org.example.resourses.Data.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserAccountServiceAnimeRepositoryTest {


    @Test
    public void addAndGetByIdAnimeAndIdUser() {
        add();
        long animeId = animeRepository.findByName(anime.getName()).getId();
        long userId = userAccountRepository.findByLogin(user.getLogin()).getId();

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
        animeRepository.add(anime);
        userAccountRepository.add(user);
    }

    private void clear(UserAccountAnime userAnimeFromDB) {
        animeRepository.remove(animeRepository.findByName(anime.getName()));
        userAccountRepository.remove(userAccountRepository.findByLogin(user.getLogin()));
        userAccountAnimeRepository.remove(userAnimeFromDB);
    }

    @Test
    public void updateUserAnime() {
        add();
        long animeId = animeRepository.findByName(anime.getName()).getId();
        long userId = userAccountRepository.findByLogin(user.getLogin()).getId();

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
        long animeId = animeRepository.findByName(anime.getName()).getId();
        long userId = userAccountRepository.findByLogin(user.getLogin()).getId();

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
        assertEquals(8, userAccountAnimeList.size());
    }
}
