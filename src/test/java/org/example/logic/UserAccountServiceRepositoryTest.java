package org.example.logic;

import org.example.entities.UserAccount;
import org.example.repository.UserAccountRepository;
import org.example.service.DirectConnectionBuilder;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserAccountServiceRepositoryTest {
    UserAccountRepository userAccountRepository = new UserAccountRepository(new DirectConnectionBuilder());
    UserAccount userAccount = new UserAccount(null,
            "Jastin", "bak12", "pas", "img");

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
        userAccountList.stream()
                .map(UserAccount::toString)
                .forEach(System.out::println);
    }
}
