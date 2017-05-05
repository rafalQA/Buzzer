package com.rafalqa.controller;

import com.rafalqa.model.Message;
import com.rafalqa.model.MessageDto;
import com.rafalqa.service.DaoMessageService;
import com.rafalqa.service.MessagingService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * Created by rpiotrowicz on 2017-04-11.
 */

@Controller
public class MessageController {


    private MessagingService messagingService;
    private DaoMessageService daoMessageService;

    public MessageController(MessagingService messagingService, DaoMessageService daoMessageService){
        this.messagingService = messagingService;
        this.daoMessageService = daoMessageService;
    }

    @MessageMapping("/hello")
    public void sendMessage(MessageDto messageDto, Principal principal) throws InterruptedException {
        String recipient = messageDto.getRecipient();
        String user = principal.getName();

        Message message = new Message(messageDto.getContent());
        message.setSenderName(user);
        message.setRecipient(recipient);

        daoMessageService.saveMessageForUser(user, message);

        messagingService.convertAndSendToUser(recipient, message);
    }
}
