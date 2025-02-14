package com.users.repository;

import com.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    @Query(value = "SELECT p FROM User p WHERE p.email LIKE %:email%")
    Optional<User> findByEmail (@Param("email") String email);

    @Query(value = "SELECT p FROM User p WHERE p.userName LIKE %:username%")
    Optional<User> findByUserName (@Param("username") String username);
}
