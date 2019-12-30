package com.ttnny.apps.services;

import org.springframework.stereotype.Service;

@Service
public class GhLangStatsService {
    public String[] getLabels(String username) {
        return new String[]{"C#", "Java", "Go", "SQL", "HTML"};
    }

    public long[] getValues(String username) {
        return new long[]{50, 275, 130, 90, 180};
    }
}