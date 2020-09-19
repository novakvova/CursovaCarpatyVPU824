package com.example.CarpathiansBlog.controllers;

import com.example.CarpathiansBlog.dto.UserDto;
import com.example.CarpathiansBlog.repo.UserRepository;
import com.example.CarpathiansBlog.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
public class RegistrationController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(UserDto userDto) {
        return "registration";
    }

    @GetMapping("/add-error")
    public String error() {
        return "add-error";
    }

    @PostMapping("/registration")
    public String addUser(@Valid UserDto userDto, BindingResult bindingResult, Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if (userRepository.findByEmail(userDto.getEmail()) != null || userRepository.findByUsername(userDto.getUsername()) != null) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }
        // get site url
        String siteUrl = request.getRequestURL().substring(0, request.getRequestURL().indexOf(request.getRequestURI()));
        if (userDetailsService.addUser(userDto, siteUrl)) {
            model.addAttribute("message", "User successfully registered! Please, confirm your account! Check your email!");
            return "add-succeed";
        } else {
            return "redirect:/add-error";
        }
    }

    @GetMapping("/activate/{code}")
    public String activateUser(@PathVariable String code, Model model) {
        boolean isActivated = userDetailsService.activateUser(code);

        if (isActivated) {
            model.addAttribute("message", "User successfully activated!");
            return "add-succeed";
        }
        model.addAttribute("message", "Activation code is not found!");
        return "login";
    }
}
