package com.epam.account.service;

import com.epam.account.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
