package com.example.CarpathiansBlog.repo;

import com.example.CarpathiansBlog.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
