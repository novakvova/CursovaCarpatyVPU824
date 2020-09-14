package com.example.CarpathiansBlog.controllers;

import com.example.CarpathiansBlog.models.Role;
import com.example.CarpathiansBlog.models.User;
import com.example.CarpathiansBlog.repo.RoleRepository;
import com.example.CarpathiansBlog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

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
        if (userRepository.findByEmail(user.getEmail()) != null) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }

        try {
            User userNew = new User();
            userNew.setEmail(user.getEmail());
            userNew.setUsername(user.getUsername());
            userNew.setPassword(passwordEncoder.encode(user.getPassword()));
            userNew.setActive(true);
            Role roleAdmin = roleRepository.findByName("ADMIN");
            userNew.addRole(roleAdmin);
            userRepository.save(userNew);
            return "redirect:/index";

        } catch (Exception ex) {
            return "redirect:/add-error";
        }
    }
}
