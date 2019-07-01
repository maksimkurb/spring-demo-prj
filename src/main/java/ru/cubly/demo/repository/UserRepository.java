package ru.cubly.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.cubly.demo.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}
