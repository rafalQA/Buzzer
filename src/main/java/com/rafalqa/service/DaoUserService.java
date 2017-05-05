package com.rafalqa.service;

import com.rafalqa.repository.UserRepository;
import com.rafalqa.model.User;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by rpiotrowicz on 2017-04-14.
 */
@Service
public class DaoUserService {

    private UserRepository userRepository;

    public DaoUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

     public Set<String> getUsersNames(){
        return userRepository.findAll().stream()
                .map(User::getUsername).collect(Collectors.toSet());
    }
}
