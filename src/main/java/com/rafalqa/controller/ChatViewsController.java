package com.rafalqa.controller;

import com.rafalqa.service.DaoUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Set;

/**
 * Created by rpiotrowicz on 2017-04-13.
 */
@Controller
public class ChatViewsController {

    private DaoUserService userService;

    public ChatViewsController(DaoUserService userService){
        this.userService = userService;
    }

    @RequestMapping("/chat")
    public String getChat(Model model, Principal principal){
        Set<String> usersNames = userService.getUsersNames();

        model.addAttribute("usersNames", usersNames);
        model.addAttribute("user", principal.getName());

        return "chat";
    }
}
