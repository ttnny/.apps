package com.ttnny.apps.services;

import org.springframework.stereotype.Service;

@Service
public class GhLangStatsService {
    public LangStats getLangStats() {
        LangStats langStats = new LangStats();

        langStats.labels = new String[]{"C#", "Java", "Go", "SQL", "HTML"};
        langStats.values = new long[]{50, 275, 130, 90, 180};

        return langStats;
    }

    private static class LangStats {
        String[] labels;
        long[] values;

        public String[] getLabels() {
            return labels;
        }

        public long[] getValues() {
            return values;
        }
    }
}