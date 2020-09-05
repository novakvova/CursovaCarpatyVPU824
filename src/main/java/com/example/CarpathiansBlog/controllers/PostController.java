package com.example.CarpathiansBlog.controllers;

import com.example.CarpathiansBlog.models.Post;
import com.example.CarpathiansBlog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Controller
public class PostController {
    private final PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/post/add-post")
    public String addPostTest(Model model) {
        model.addAttribute("pageTitle", "Posts page");
        return "add-post";
    }

    @PostMapping("/post/add-post")
    public String loadFile(@RequestParam("file") MultipartFile file,
                           @RequestParam String title,
                           @RequestParam String anons,
                           @RequestParam String fullText,
                           Model model
    ) {

        Post post;
        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            try {
                Path path = Paths.get("src/main/resources/static/images");
                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + file.getOriginalFilename();
                post = new Post();
                post.setAnons(anons);
                post.setFullText(fullText);
                post.setTitle(title);
                post.setViews(0);
                InputStream inputStream = file.getInputStream();
                Files.copy(inputStream, path.resolve(resultFileName), StandardCopyOption.REPLACE_EXISTING);
                post.setImage(resultFileName);
                postRepository.save(post);
                model.addAttribute("pageTitle", "Succeed");
                return "add-succeed";

            } catch (Exception e) {
                model.addAttribute("pageTitle", "Error");
                return "add-error";
            }
        }

        return "add-error";
    }

    @GetMapping("/post/{id}")
    public String showPost(@PathVariable("id") long id, Model model){
        Post post = postRepository.findById(id).orElse(new Post());
        model.addAttribute("post", post);
        model.addAttribute("pageTitle", "Post");
        return "post";
    }
}
