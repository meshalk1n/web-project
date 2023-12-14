package com.example.project.controllers;

import com.example.project.services.PersonService;
import com.example.project.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main/")
public class MainController {

    private final PersonService personServices;

    private final TasksService tasksServices;

    @Autowired
    public MainController(PersonService personServices, TasksService tasksServices) {
        this.personServices = personServices;
        this.tasksServices = tasksServices;
    }

    @GetMapping()
    public String mainPage(Model model){
        model.addAttribute("people", personServices.findAll());
        return "main/main";
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "admin";
    }

}
