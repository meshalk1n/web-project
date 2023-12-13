package com.example.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class ActionSelectionFormController {

    @GetMapping("/action-selection-form")
    public String form(){
        return "auth/action-selection-form";
    }
}
