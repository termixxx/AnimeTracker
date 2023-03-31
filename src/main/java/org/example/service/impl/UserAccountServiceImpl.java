package org.example.service.impl;

import org.example.entities.UserAccount;
import org.example.repository.UserAccountRepository;
import org.example.service.UserAccountService;
import org.example.utils.PullConnectionBuilder;

public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepository userAccountRepository = new UserAccountRepository(new PullConnectionBuilder());

    @Override
    public boolean createUser(UserAccount userAccount) {
        userAccount.setPassword(getPasswordHash(userAccount.getPassword()));
        userAccount.setPictureURL("https://clck.ru/33vB6K");
        return userAccountRepository.add(userAccount);
    }

    @Override
    public UserAccount loginUser(String login, String password) {
        UserAccount userAccount = userAccountRepository.findByLogin(login);
        if (userAccount != null &&
                userAccount.getPassword().equals(getPasswordHash(password))) {
            return userAccount;
        }
        return null;
    }

    private static String getPasswordHash(String password) {
        return String.valueOf(password.hashCode());
    }


}
