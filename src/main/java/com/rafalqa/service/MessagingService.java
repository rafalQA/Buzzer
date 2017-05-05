package com.rafalqa.service;

import com.rafalqa.model.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by rpiotrowicz on 2017-04-18.
 */
@Service
public class MessagingService {

    private SimpMessagingTemplate messagingTemplate;

    public MessagingService(SimpMessagingTemplate messagingTemplate){
        this.messagingTemplate = messagingTemplate;
    }

    public void convertAndSendToUser(String user, Message message){
        messagingTemplate.convertAndSendToUser(user, "/queue/messenger", message);
    }
}
