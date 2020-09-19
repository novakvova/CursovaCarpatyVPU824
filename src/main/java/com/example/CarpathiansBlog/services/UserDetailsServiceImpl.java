package com.example.CarpathiansBlog.services;

import com.example.CarpathiansBlog.dto.UserDto;
import com.example.CarpathiansBlog.models.Role;
import com.example.CarpathiansBlog.models.User;
import com.example.CarpathiansBlog.repo.RoleRepository;
import com.example.CarpathiansBlog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return new MyUserDetails(user);
    }

    public boolean addUser(UserDto userDto, String siteUrl) {
        User userDB = userRepository.findByUsername(userDto.getUsername());
        if (userDB != null) {
            return false;
        }

        try {
            User userNew = new User();
            userNew.setEmail(userDto.getEmail());
            userNew.setUsername(userDto.getUsername());
            userNew.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userNew.setActive(false);
            userNew.setActiveCode(UUID.randomUUID().toString());
            Role roleAdmin = roleRepository.findByName("USER");
            userNew.addRole(roleAdmin);
            userRepository.save(userNew);

            if (!StringUtils.isEmpty(userNew.getEmail())) {
                String message = String.format(
                        "Hello, %s \n" +
                                "Welcome to our site. \n" +
                                "Please, visit next link: %s/activate/%s", userNew.getUsername(), siteUrl, userNew.getActiveCode());
                mailSender.sendSimpleMessage(userNew.getEmail(), "Activation code", message);
            }
            return true;

        } catch (Exception ex) {
            return false;
        }
    }

    public boolean activateUser(String code){
        User userAct = userRepository.findByActiveCode(code);
        if(userAct == null){
            return false;
        }
        userAct.setActive(true);
        userAct.setActiveCode(null);
        userRepository.save(userAct);

        return true;
    }
}
