package com.ttnny.apps.controllers;

import com.ttnny.apps.services.GhLangStatsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GhLangStatsController {
    private GhLangStatsService service;

    public GhLangStatsController(GhLangStatsService service) {
        this.service = service;
    }

    @RequestMapping("/gh-langstats")
    private String ghLangStats(Model model) {
        // Set title of the page
        model.addAttribute("title", "GitHub Language Chart :: Tony's Spacetime");

        // Get langstats for front-end to build charts
        model.addAttribute("langStats", service.getLangStats());

        model.addAttribute("errors", "");

        return "apps/gh-langstats";
    }
}