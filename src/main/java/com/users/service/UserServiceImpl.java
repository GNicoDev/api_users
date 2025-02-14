package com.users.service;

import com.users.entity.User;
import com.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    UserSecurityService userSecurityService;
    private final PasswordEncoder passwordEncoder; // Declare PasswordEncoder attribute
    @Autowired

    public UserServiceImpl(UserRepository userRepository, UserSecurityService userSecurityService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userSecurityService = userSecurityService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> createUser(User user) {
        if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            return Optional.empty();
        }

        String hashedPassword = userSecurityService.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        return Optional.of(userRepository.save(user));
    }

    public Optional<User> updateUser(User user, String id) {
        Optional<User> existingUserOptional = userRepository.findById(id);

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();

            // Update the fields of the existing user with the new values
            existingUser.setUserName(user.getUserName());
            existingUser.setEmail(user.getEmail());
            existingUser.setLocked(user.getLocked());
            existingUser.setDisabled(user.getDisabled());
            existingUser.setRole(user.getRole());

            // Encrypt the password before updating
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }

            userRepository.save(existingUser);
            return Optional.of(existingUser);
        }

        return Optional.empty();
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
