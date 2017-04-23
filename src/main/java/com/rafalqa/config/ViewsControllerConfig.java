package com.rafalqa.config;

import com.rafalqa.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

/**
 * Created by rpiotrowicz on 2017-04-13.
 */
@Controller
public class ViewsControllerConfig {

    private UserService userService;

    public ViewsControllerConfig(UserService userService){
        this.userService = userService;
    }

    @RequestMapping("/chat")
    public String getChat(Model model){
        Set<String> usersNames = userService.getUsersNames();

        model.addAttribute("usersNames", usersNames);

        return "chat";
    }
}
