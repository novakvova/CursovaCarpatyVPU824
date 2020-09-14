package com.example.CarpathiansBlog.seeders;

import com.example.CarpathiansBlog.models.Role;
import com.example.CarpathiansBlog.models.User;
import com.example.CarpathiansBlog.repo.RoleRepository;
import com.example.CarpathiansBlog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UsersSeeder {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public void run() {
        User oldUser = userRepository.findByEmail("admin@gmail.com");
        if (oldUser == null) {
            User user = new User();
            user.setEmail("admin@gmail.com");
            user.setUsername("smethan");
            user.setPassword(passwordEncoder.encode("123456"));
            user.setActive(true);

            Role roleAdmin = roleRepository.findByName("ADMIN");
            user.addRole(roleAdmin);
            userRepository.save(user);
        }
    }
}
