package com.example.CarpathiansBlog.controllers;

import com.example.CarpathiansBlog.models.Post;
import com.example.CarpathiansBlog.models.User;
import com.example.CarpathiansBlog.repo.PostRepository;
import com.example.CarpathiansBlog.services.StorageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;

@Controller
public class PostController {
    private final PostRepository postRepository;
    private final StorageService storageService;

    public PostController(PostRepository postRepository, StorageService storageService) {
        this.postRepository = postRepository;
        this.storageService = storageService;
    }

    @GetMapping("/post/add-post")
    public String addPostTest(Model model) {
        model.addAttribute("pageTitle", "Posts page");
        return "add-post";
    }

    @PostMapping("/post/add-post")
    public String loadFile(@AuthenticationPrincipal User user,
                           @RequestParam("file") MultipartFile file,
                           @RequestParam String title,
                           @RequestParam String anons,
                           @RequestParam String fullText,
                           Model model
    ) {

        Post post;
        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            try {
                String name = UUID.randomUUID().toString()+"." +
                        FilenameUtils.getExtension(file.getOriginalFilename());

                byte[] bytes = file.getBytes();

                Path f = storageService.load("");
                String rootPath= f.toUri().getPath();
                System.out.println("---------" + rootPath);
                File dir = new File(rootPath + File.separator );
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                post = new Post();
                post.setImage(name);
                post.setAnons(anons);
                post.setFullText(fullText);
                post.setTitle(title);
                post.setViews(0);
                post.setAuthor(user);
                postRepository.save(post);
                model.addAttribute("pageTitle", "Succeed");
                return "add-succeed";

            } catch (Exception e) {
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
