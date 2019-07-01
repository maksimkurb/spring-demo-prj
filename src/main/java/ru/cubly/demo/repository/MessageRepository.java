package ru.cubly.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.cubly.demo.entity.Message;

public interface MessageRepository extends CrudRepository<Message, Integer> {
}
