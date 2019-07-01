package ru.cubly.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.cubly.demo.entity.Message;
import ru.cubly.demo.service.MessageService;

import javax.validation.Valid;

@Controller
@RequestMapping("/messages")
public class MessageController {
    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("message", new Message());

        return "messages";
    }

    @PostMapping
    public String add(@Valid final Message message, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "messages";
        }

        messageService.save(message);
        model.clear();
        return "redirect:/messages";
    }

    @ModelAttribute("messages")
    public Iterable<Message> populateMessages() {
        return this.messageService.findAll();
    }
}
