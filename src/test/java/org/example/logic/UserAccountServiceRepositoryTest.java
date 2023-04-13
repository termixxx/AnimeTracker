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
        UserAccount founded = userAccountRepository.findByLogin(userAccount.getName());
        assertEquals(userAccount.toTest(), founded.toTest());

        userAccountRepository.remove(userAccountRepository.findByLogin(userAccount.getName()));
    }

    @Test
    public void removeUser() {
        userAccountRepository.add(userAccount);

        UserAccount userAccountFounded = userAccountRepository.findByLogin(userAccount.getName());
        userAccountRepository.remove(userAccountFounded);
        assertNull(userAccountRepository.findByLogin(userAccountFounded.getName()));
    }

    @Test
    public void updateUser() {
        userAccountRepository.add(userAccount);

        UserAccount userAccountNew = new UserAccount(
                userAccountRepository.findByLogin(userAccount.getName()).getId(),
                "Robin",
                "bak12",
                "pas",
                "img");
        userAccountRepository.update(userAccountNew);

        assertEquals(userAccountNew.toTest(),
                userAccountRepository.findByLogin(userAccountNew.getName()).toTest());

        userAccountRepository.remove(userAccountRepository.findByLogin(userAccountNew.getName()));
    }

    @Test
    public void getAll() {
        List<UserAccount> userAccountList = userAccountRepository.getAll();
        assertEquals(20, userAccountList.size());
    }
}
