package org.example.service;

import org.example.entities.UserAccount;

public interface UserAccountService {
    boolean createUser(UserAccount userAccount);

    UserAccount loginUser(String login, String password);

    UserAccount findByLogin(String login);
}
