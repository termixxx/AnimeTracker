package org.example.logic;

import org.example.entities.UserAccount;
import org.example.service.UserAccountService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserAccountServiceTest {
    UserAccountService userAccountService = new UserAccountService();

    @Test
    public void addAndGetUserById() {
        UserAccount userAccount = new UserAccount(null,
                "Jastin",
                "bak12",
                "pas",
                "img");

        userAccountService.add(userAccount);
        UserAccount founded = userAccountService.findByName("Jastin");


        assertEquals(userAccount.toTest(), founded.toTest());
    }

    @Test
    public void removeUser() {
        UserAccount saga = userAccountService.findByName("Robin");
        userAccountService.remove(saga);
        assertNull(userAccountService.findByName(saga.getName()));
    }

    @Test
    public void updateUser() {
        UserAccount userAccount = new UserAccount(null,
                "Jastin",
                "bak12",
                "pas",
                "img");
        userAccountService.add(userAccount);

        UserAccount userAccountNew = new UserAccount(
                userAccountService.findByName(userAccount.getName()).getId(),
                "Robin",
                "bak12",
                "pas",
                "img");
        userAccountService.update(userAccountNew);

        assertEquals(userAccountNew.toTest(),
                userAccountService.findByName(userAccountNew.getName()).toTest());
    }

    @Test
    public void getAll() {
        List<UserAccount> userAccountList = userAccountService.getAll();
        userAccountList.stream()
                .map(UserAccount::toString)
                .forEach(System.out::println);
    }
}
