package com.example.CarpathiansBlog.controllers;

import com.example.CarpathiansBlog.dto.UserDto;
import com.example.CarpathiansBlog.models.Role;
import com.example.CarpathiansBlog.models.User;
import com.example.CarpathiansBlog.repo.RoleRepository;
import com.example.CarpathiansBlog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/registration")
    public String registration(UserDto userDto) {
        return "registration";
    }

    @GetMapping("/add-error")
    public String error() {
        return "add-error";
    }

    @PostMapping("/registration")
    public String addUser(@Valid UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if (userRepository.findByEmail(userDto.getEmail()) != null || userRepository.findByUsername(userDto.getUsername()) != null) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }

        try {
            User userNew = new User();
            userNew.setEmail(userDto.getEmail());
            userNew.setUsername(userDto.getUsername());
            userNew.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userNew.setActive(true);

//            Role roleAdmin = roleRepository.findByName("ADMIN");
//            userNew.addRole(roleAdmin);

            Role roleUser = roleRepository.findByName("USER");
            userNew.addRole(roleUser);

            userRepository.save(userNew);
            return "redirect:/index";

        } catch (Exception ex) {
            return "redirect:/add-error";
        }
    }
}
