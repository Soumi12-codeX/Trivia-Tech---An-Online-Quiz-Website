package com.portal.mcq_portal.service;

import com.portal.mcq_portal.model.User;
import com.portal.mcq_portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public User getUserFromToken(String token) {
        System.out.println("=== AuthService Called ===");
        System.out.println("Token received: " + token);

        if (token == null || token.isEmpty()) {
            System.out.println("Token is null or empty. Returning null user.");
            return null;
        }
        
        User user = userRepository.findByEmail(token).orElse(null);
        if (user != null) {
            System.out.println("User found: " + user.getName());
        } else {
            System.out.println("User NOT found for token: " + token);
        }
        
        return user;
    }
}