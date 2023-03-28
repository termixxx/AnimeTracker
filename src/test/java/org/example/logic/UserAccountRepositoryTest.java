package org.example.logic;

import org.example.entities.UserAccount;
import org.example.repository.UserAccountRepository;
import org.example.service.DirectConnectionBuilder;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserAccountRepositoryTest {
    UserAccountRepository userAccountRepository = new UserAccountRepository(new DirectConnectionBuilder());
    UserAccount userAccount = new UserAccount(null,
            "Jastin", "bak12", "pas", "img");

    @Test
    public void addAndGetUserById() {
        userAccountRepository.add(userAccount);
        UserAccount founded = userAccountRepository.findByName(userAccount.getName());
        assertEquals(userAccount.toTest(), founded.toTest());

        userAccountRepository.remove(userAccountRepository.findByName(userAccount.getName()));
    }

    @Test
    public void removeUser() {
        userAccountRepository.add(userAccount);

        UserAccount userAccountFounded = userAccountRepository.findByName(userAccount.getName());
        userAccountRepository.remove(userAccountFounded);
        assertNull(userAccountRepository.findByName(userAccountFounded.getName()));
    }

    @Test
    public void updateUser() {
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

        userAccountRepository.remove(userAccountRepository.findByName(userAccountNew.getName()));
    }

    @Test
    public void getAll() {
        List<UserAccount> userAccountList = userAccountRepository.getAll();
        userAccountList.stream()
                .map(UserAccount::toString)
                .forEach(System.out::println);
    }
}
