package com.example.CarpathiansBlog.repo;

import com.example.CarpathiansBlog.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
}
