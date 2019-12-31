package com.ttnny.apps.services;

import org.springframework.stereotype.Service;

@Service
public class GhCtbnGraphService {
    public String getURL(String username) {
        // Set API endpoint
        return "https://61lfz00oi9.execute-api.us-west-2.amazonaws.com/prod/ctbnstats/" + username;
    }
}