package com.example.CarpathiansBlog.repo;

import com.example.CarpathiansBlog.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
    Post findByTitle(String title);
}
