package com.example.project.controllers;


import com.example.project.models.Person;
import com.example.project.services.RegistrationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationServices registrationServices;

    @Autowired
    public AuthController(RegistrationServices registrationServices) {
        this.registrationServices = registrationServices;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }
    @GetMapping("/registration")
    public String FormRegistration(@ModelAttribute("person") Person person){
        return "auth/registration";
    }
    @PostMapping("/registration")
    public String registration(@ModelAttribute("person") Person person){
        registrationServices.register(person);
        return "redirect:/auth/login";
    }
}