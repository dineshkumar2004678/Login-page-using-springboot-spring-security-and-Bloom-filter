package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BloomFilterService bloomFilterService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        
        // Add to bloom filters
        bloomFilterService.addUsername(user.getName());
        bloomFilterService.addEmail(user.getEmail());
        
        return savedUser;
    }

    // Keep other methods as they are for registration
    public User findByEmail(String email) {
        // Check bloom filter first
        if (!bloomFilterService.mightContainEmail(email)) {
            return null;
        }
        return userRepository.findByEmail(email);
    }

    public User findByUsername(String username) {
        // Check bloom filter first
        if (!bloomFilterService.mightContainUsername(username)) {
            return null;
        }
        return userRepository.findByName(username);
    }

    public boolean existsByEmail(String email) {
        // Check bloom filter first
        if (!bloomFilterService.mightContainEmail(email)) {
            return false;
        }
        return userRepository.existsByEmail(email);
    }

    public boolean existsByUsername(String username) {
        // Check bloom filter first
        if (!bloomFilterService.mightContainUsername(username)) {
            return false;
        }
        return userRepository.existsByName(username);
    }
}