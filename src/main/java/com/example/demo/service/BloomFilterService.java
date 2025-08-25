package com.example.demo.service;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;

@Service
public class BloomFilterService {
    private BloomFilter<String> usernameBloomFilter;
    private BloomFilter<String> emailBloomFilter;
    private static final int EXPECTED_INSERTIONS = 100000;
    private static final double FPP = 0.01;

    public BloomFilterService() {
        usernameBloomFilter = BloomFilter.create(
            Funnels.stringFunnel(StandardCharsets.UTF_8), 
            EXPECTED_INSERTIONS, 
            FPP
        );
        emailBloomFilter = BloomFilter.create(
            Funnels.stringFunnel(StandardCharsets.UTF_8), 
            EXPECTED_INSERTIONS, 
            FPP
        );
    }

    public void addUsername(String username) {
        usernameBloomFilter.put(username);
    }

    public void addEmail(String email) {
        emailBloomFilter.put(email);
    }

    public boolean mightContainUsername(String username) {
        return usernameBloomFilter.mightContain(username);
    }

    public boolean mightContainEmail(String email) {
        return emailBloomFilter.mightContain(email);
    }
}