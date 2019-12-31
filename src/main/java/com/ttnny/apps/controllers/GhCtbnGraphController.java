package com.ttnny.apps.controllers;

import com.ttnny.apps.models.GhCtbnGraphModel;
import com.ttnny.apps.models.GhLangStatsModel;
import com.ttnny.apps.services.GhCtbnGraphService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GhCtbnGraphController {
    private GhCtbnGraphService service;

    private GhCtbnGraphController(GhCtbnGraphService service) {
        this.service = service;
    }

    @GetMapping("/gh-ctbngraph")
    public String getGhCtbnGraph(Model model) {
        // Set title of the page
        model.addAttribute("title", "GitHub Contribution Graph :: Tony's Spacetime");
        model.addAttribute("ghCtbnGraphModel", new GhCtbnGraphModel());

        return "apps/gh-ctbngraph";
    }

    @PostMapping("/gh-ctbngraph")
    public String postGhCtbnGraph(Model model, @ModelAttribute GhCtbnGraphModel ghCtbnGraphModel) {
        // Set title of the page
        model.addAttribute("title", "GitHub Contribution Graph :: Tony's Spacetime");
        model.addAttribute("hasErrors", false);

        // Get the source of the generated SVG
        String username = ghCtbnGraphModel.getUsername();
        String svgURL = service.getURL(username);

        if (svgURL != null) {
            ghCtbnGraphModel.setSvgURL(svgURL);
        } else {
            model.addAttribute("hasErrors", true);
        }

        return "apps/gh-ctbngraph";
    }
}