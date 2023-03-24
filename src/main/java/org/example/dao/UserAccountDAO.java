package org.example.dao;

import org.example.entities.UserAccount;

import java.util.List;

public interface UserAccountDAO {
    // create
    void add(UserAccount userAccount);

    // read
    List<UserAccount> getAll();

    UserAccount getById(Long id);

    UserAccount findByName(String name);

    // update
    void update(UserAccount userAccount);

    // delete
    void remove(UserAccount userAccount);
}
