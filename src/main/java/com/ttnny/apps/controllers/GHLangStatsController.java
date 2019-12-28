package com.ttnny.apps.controllers;

import com.ttnny.apps.services.GHLangStatsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class GHLangStatsController {
    GHLangStatsService service = new GHLangStatsService();

    /**
     * Route: index
     */
    @RequestMapping(path = {"/gh-langstats"})
    private String index() {
        return "apps/gh-langstats";
    }
}
