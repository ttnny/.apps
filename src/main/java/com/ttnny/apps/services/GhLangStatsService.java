package com.ttnny.apps.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttnny.apps.models.GhLangStatsModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class GhLangStatsService {
    GhLangStatsModel langStats = new GhLangStatsModel();

    public void getLangStats(String username) {
        // Set API endpoint
        final String url = "https://61lfz00oi9.execute-api.us-west-2.amazonaws.com/prod/langstats/" + username;

        // Send GET request
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        // Parse the response and convert to arrays
        try {
            ObjectMapper mapper = new ObjectMapper();

            // Map JSON (String) response to key/value pairs
            assert response != null;
            Map<String, Long> data = mapper.readValue(response, new TypeReference<>() {
            });

            List<String> labelList = new ArrayList<>();
            List<Long> valueList = new ArrayList<>();

            data.forEach((k, v) -> {
                labelList.add(k);
                valueList.add(v);
            });

            langStats.setLabels(labelList.toArray(new String[0]));
            langStats.setValues(valueList.toArray(new Long[0]));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public String[] getLabels() {
        return langStats.getLabels();
    }

    public Long[] getValues() {
        return langStats.getValues();
    }
}