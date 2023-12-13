package com.example.project.controllers;


import com.example.project.security.PersonDetails;
import com.example.project.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/main/")
public class ProfileController {

    private PersonDetailsService personDetailsService;

    @Autowired
    public ProfileController(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @GetMapping("/profile/")
    public String profilePage(Model model, Principal principal){
        PersonDetails personDetails = (PersonDetails) personDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("person", personDetails.getPerson());
        return "main/profile";
    }
}
