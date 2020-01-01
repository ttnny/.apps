package com.ttnny.apps.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/*")
    private String index() {
        return "index";
    }

    @RequestMapping("info")
    private String info() {
        return "info";
    }
}