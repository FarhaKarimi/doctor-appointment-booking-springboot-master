package com.nadimnesar.drappointment.service;

import com.nadimnesar.drappointment.model.User;
import com.nadimnesar.drappointment.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username);
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public String getRole(Authentication authentication) {
        String role = "ANONYMOUS";
        if (authentication != null) {
            String username = ((UserDetails) authentication.getPrincipal()).getUsername();
            User user = findUserByUsername(username);
            if (user != null) role = user.getRole().name();
        }
        return role;
    }
}