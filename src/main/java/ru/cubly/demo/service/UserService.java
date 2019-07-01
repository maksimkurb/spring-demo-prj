package ru.cubly.demo.service;

import org.springframework.stereotype.Service;
import ru.cubly.demo.entity.Role;
import ru.cubly.demo.entity.User;
import ru.cubly.demo.repository.UserRepository;

import javax.persistence.NonUniqueResultException;
import java.util.Collections;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) throws NonUniqueResultException, NullPointerException {
        if (user == null) throw new NullPointerException("User can't be null");
        if (userRepository.findByUsername(user.getUsername()) != null)
            throw new NonUniqueResultException("User already exists");

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));

        return this.userRepository.save(user);
    }
}
