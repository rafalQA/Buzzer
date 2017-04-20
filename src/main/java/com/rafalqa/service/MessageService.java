package com.rafalqa.service;

import com.rafalqa.model.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by rpiotrowicz on 2017-04-18.
 */
@Service
public class MessageService {

    private SimpMessagingTemplate messagingTemplate;
    
    public MessageService(SimpMessagingTemplate messagingTemplate){
        this.messagingTemplate = messagingTemplate;
    }

    public void convertAndSendToUser(String user, Message message){
        messagingTemplate.convertAndSendToUser(user, "/queue/messenger", message);
    }
}
