package com.rafalqa.service;

import com.rafalqa.model.Message;
import com.rafalqa.model.User;
import com.rafalqa.repository.MessageRepository;
import com.rafalqa.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by rpiotrowicz on 2017-04-26.
 */
@Service
public class DaoMessageService {

    private MessageRepository messageRepository;
    private UserRepository userRepository;

    public DaoMessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }


    public Long saveMessageForUser(String userName, Message message) {
        Optional<User> user = userRepository.findByUserName(userName);

        message.setUser(user.orElseThrow(()
                -> new UsernameNotFoundException("There is no user " + userName)));

        return messageRepository.save(message).getMessageId();
    }


}
