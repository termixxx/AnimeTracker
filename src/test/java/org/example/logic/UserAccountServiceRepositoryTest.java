package org.example.logic;

import org.example.entities.UserAccount;
import org.junit.Test;

import java.util.List;

import static org.example.resourses.Data.userAccount;
import static org.example.resourses.Data.userAccountRepository;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserAccountServiceRepositoryTest {
    @Test
    public void addAndGetUserById() {
        userAccountRepository.add(userAccount);
        UserAccount founded = userAccountRepository.findByLogin(userAccount.getLogin());
        assertEquals(userAccount.toTest(), founded.toTest());

        userAccountRepository.remove(userAccountRepository.findByLogin(userAccount.getLogin()));
    }

    @Test
    public void removeUser() {
        userAccountRepository.add(userAccount);

        UserAccount userAccountFounded = userAccountRepository.findByLogin(userAccount.getLogin());
        userAccountRepository.remove(userAccountFounded);
        assertNull(userAccountRepository.findByLogin(userAccountFounded.getLogin()));
    }

    @Test
    public void updateUser() {
        userAccountRepository.add(userAccount);

        UserAccount userAccountNew = new UserAccount(
                userAccountRepository.findByLogin(userAccount.getLogin()).getId(),
                "Robin",
                "bak12",
                "pas",
                "img");
        userAccountRepository.update(userAccountNew);

        assertEquals(userAccountNew.toTest(),
                userAccountRepository.findByLogin(userAccountNew.getLogin()).toTest());

        userAccountRepository.remove(userAccountRepository.findByLogin(userAccountNew.getLogin()));
    }

    @Test
    public void getAll() {
        List<UserAccount> userAccountList = userAccountRepository.getAll();
        assertEquals(21, userAccountList.size());
    }
}
