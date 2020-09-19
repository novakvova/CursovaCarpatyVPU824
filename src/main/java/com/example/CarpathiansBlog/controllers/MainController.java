package com.example.CarpathiansBlog.controllers;

import com.example.CarpathiansBlog.models.Post;
import com.example.CarpathiansBlog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public String mainRedirect() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String main(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("pageTitle", "Home page");
        return "index";
    }

    @GetMapping("/photos")
    public String showPhotos(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("pageTitle", "Photos");
        return "photos";
    }

    @GetMapping("/bio")
    public String showBio(Model model) {
        model.addAttribute("pageTitle", "About Us");
        return "bio";
    }
}
