package com.example.spring.controllers;

import com.example.spring.entity.MailMassage;
import com.example.spring.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/mail/send")
    public void createCar(@RequestBody MailMassage message) {
        mailService.sendMail(message.getAddress(), message.getTitle(), message.getMessageText());
    }
}
