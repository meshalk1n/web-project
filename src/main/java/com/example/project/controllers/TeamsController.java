package com.example.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main/")
public class TeamsController {

    @GetMapping("/teams")
    public String tasksPage(){
        return "main/teams";
    }
}
