package com.ttnny.apps.controllers;

import com.ttnny.apps.models.GhLangStatsModel;
import com.ttnny.apps.services.GhLangStatsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GhLangStatsController {
    private GhLangStatsService service;

    private GhLangStatsController(GhLangStatsService service) {
        this.service = service;
    }

    @GetMapping("/gh-langstats")
    public String getGhLangStats(Model model) {
        // Set title of the page
        model.addAttribute("title", "GitHub Language Chart :: Tony's Spacetime");
        model.addAttribute("ghLangStatsModel", new GhLangStatsModel());

        return "apps/gh-langstats";
    }

    @PostMapping("/gh-langstats")
    public String postGhLangStats(Model model, @ModelAttribute GhLangStatsModel ghLangStatsModel) {
        // Set title of the page
        model.addAttribute("title", "GitHub Language Chart :: Tony's Spacetime");

        // Calculate langstats
        String username = ghLangStatsModel.getUsername();

        ghLangStatsModel.setLabels(service.getLabels(username));
        ghLangStatsModel.setValues(service.getValues(username));

        return "apps/gh-langstats";
    }
}