package com.rafalqa.controller;

import com.rafalqa.model.Message;
import com.rafalqa.model.MessageDto;
import com.rafalqa.service.MessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * Created by rpiotrowicz on 2017-04-11.
 */

@Controller
public class MessageController {


    private MessageService messageService;

    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    @MessageMapping("/hello")
    public void sendMessage(MessageDto messageDto, Principal principal) throws InterruptedException {
        String user = messageDto.getRecipient();

        Message message = new Message(messageDto.getContent());
        message.setUserName(principal.getName());

        messageService.convertAndSendToUser(user, message);
    }
}
