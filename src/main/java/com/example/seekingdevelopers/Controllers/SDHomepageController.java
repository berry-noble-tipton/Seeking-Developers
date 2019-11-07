package com.example.seekingdevelopers.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SDHomepageController {
    @GetMapping("/")
    public String returnHomePage() {
        return "home-foundation";
    }
}