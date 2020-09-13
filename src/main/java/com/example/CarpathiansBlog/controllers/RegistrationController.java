package com.example.CarpathiansBlog.controllers;

import com.example.CarpathiansBlog.models.User;
import com.example.CarpathiansBlog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

//    private final UserService userService;
//
//    public RegistrationController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/add-error")
    public String error() {
        return "add-error";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        if (userService.isUserInDB(user)) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }
        if (userService.addUser(user)) {
            return "redirect:/index";
        } else {
            return "redirect:/add-error";
        }
    }
}
