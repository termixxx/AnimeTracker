package org.example.service.impl;

import org.example.entities.UserAccount;
import org.example.repository.UserAccountRepository;
import org.example.service.UserAccountService;
import org.example.utils.PullConnectionBuilder;

public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepository userAccountRepository = new UserAccountRepository(new PullConnectionBuilder());

    @Override
    public boolean createUser(UserAccount userAccount) {
        userAccount.setPassword(String.valueOf(userAccount.getPassword().hashCode()));
        userAccount.setPictureURL("https://clck.ru/33vB6K");
        return userAccountRepository.add(userAccount);
    }
}
