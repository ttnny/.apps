package com.ttnny.apps.models;

public class GhLangStatsModel {
    private String username;
    private String[] labels;
    private Long[] values;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    public Long[] getValues() {
        return values;
    }

    public void setValues(Long[] values) {
        this.values = values;
    }
}