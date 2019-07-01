package ru.cubly.demo.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.cubly.demo.entity.Message;
import ru.cubly.demo.repository.MessageRepository;

@Service
@Scope("prototype")
public class MessageService {
    private MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public Iterable<Message> findAll() {
        return repository.findAll();
    }

    public Message save(Message message) {
        return repository.save(message);
    }
}
