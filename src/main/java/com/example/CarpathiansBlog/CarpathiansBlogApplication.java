package com.example.CarpathiansBlog;

import com.example.CarpathiansBlog.seeders.PostsSeeder;
import com.example.CarpathiansBlog.seeders.RolesSeeder;
import com.example.CarpathiansBlog.seeders.UsersSeeder;
import com.example.CarpathiansBlog.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CarpathiansBlogApplication {
    @Autowired
    private RolesSeeder rolesSeeder;
    @Autowired
    private UsersSeeder usersSeeder;
    @Autowired
    private PostsSeeder postsSeeder;

    public static void main(String[] args) {
        SpringApplication.run(CarpathiansBlogApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            //storageService.deleteAll();
            storageService.init();
            rolesSeeder.run();
            usersSeeder.run();
            // postsSeeder.run();
        };
    }

}

