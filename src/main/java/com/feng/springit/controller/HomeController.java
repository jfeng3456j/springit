package com.feng.springit.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class HomeController {

    @GetMapping("/sampleIndex")
    public String index(Model model) {
        model.addAttribute("title", "index page");
        return "/link/index";
    }

    @GetMapping("/sampleHome") //set up the route in the application
    public String home(Model model) {
        model.addAttribute("title", "home page");
        return "home";
    }


}
