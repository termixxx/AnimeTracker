package org.example.logic;

import org.example.entities.UserAccount;
import org.example.repository.UserAccountRepository;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserAccountRepositoryTest {
    UserAccountRepository userAccountRepository = new UserAccountRepository();

    @Test
    public void addAndGetUserById() {
        UserAccount userAccount = new UserAccount(null,
                "Jastin",
                "bak12",
                "pas",
                "img");

        userAccountRepository.add(userAccount);
        UserAccount founded = userAccountRepository.findByName("Jastin");


        assertEquals(userAccount.toTest(), founded.toTest());
    }

    @Test
    public void removeUser() {
        UserAccount saga = userAccountRepository.findByName("Robin");
        userAccountRepository.remove(saga);
        assertNull(userAccountRepository.findByName(saga.getName()));
    }

    @Test
    public void updateUser() {
        UserAccount userAccount = new UserAccount(null,
                "Jastin",
                "bak12",
                "pas",
                "img");
        userAccountRepository.add(userAccount);

        UserAccount userAccountNew = new UserAccount(
                userAccountRepository.findByName(userAccount.getName()).getId(),
                "Robin",
                "bak12",
                "pas",
                "img");
        userAccountRepository.update(userAccountNew);

        assertEquals(userAccountNew.toTest(),
                userAccountRepository.findByName(userAccountNew.getName()).toTest());
    }

    @Test
    public void getAll() {
        List<UserAccount> userAccountList = userAccountRepository.getAll();
        userAccountList.stream()
                .map(UserAccount::toString)
                .forEach(System.out::println);
    }
}
