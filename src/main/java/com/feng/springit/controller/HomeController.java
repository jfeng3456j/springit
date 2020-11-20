package com.feng.springit.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

    @GetMapping("/") //set up the route in the application
    public String Home(Model model) {
        model.addAttribute("message", "Hello world");
        return "index";
    }
}
