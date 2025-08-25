package com.example.demo.repository;

import com.example.demo.service.BloomFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BloomFilterRepository {
    
    @Autowired
    private BloomFilterService bloomFilterService;
    
    public void addUsername(String username) {
        bloomFilterService.addUsername(username);
    }
    
    public void addEmail(String email) {
        bloomFilterService.addEmail(email);
    }
    
    public boolean mightContainUsername(String username) {
        return bloomFilterService.mightContainUsername(username);
    }
    
    public boolean mightContainEmail(String email) {
        return bloomFilterService.mightContainEmail(email);
    }
}