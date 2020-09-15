package com.example.CarpathiansBlog.seeders;

import com.example.CarpathiansBlog.models.Role;
import com.example.CarpathiansBlog.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RolesSeeder {
    @Autowired
    private RoleRepository roleRepository;
    @Transactional
    public void run() {
        Role oldRole = roleRepository.findByName("ADMIN");
        if (oldRole == null) {
            Role role = new Role();
            role.setName("ADMIN");
            roleRepository.save(role);
        }
        oldRole = roleRepository.findByName("USER");
        if (oldRole == null) {
            Role role = new Role();
            role.setName("USER");
            roleRepository.save(role);
        }
    }
}
