package com.users.service;

import com.users.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> listAllUsers();

    Optional<User> findUserByEmail(String email);

    Optional<User> createUser(User user);

    Optional<User> updateUser(User user, Long id);

    void deleteUser (Long id);

}
