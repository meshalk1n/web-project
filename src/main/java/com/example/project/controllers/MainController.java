package com.example.project.controllers;

import com.example.project.models.Tidings;
import com.example.project.services.NewsService;
import com.example.project.services.PersonService;
import com.example.project.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("main/")
public class MainController {

    private final PersonService personServices;

    private final TasksService tasksServices;

    private final NewsService newsService;

    @Autowired
    public MainController(PersonService personServices, TasksService tasksServices, NewsService newsService) {
        this.personServices = personServices;
        this.tasksServices = tasksServices;
        this.newsService = newsService;
    }
    @PostMapping()
    public String processSaveNewBook(@ModelAttribute("tidings") @Valid Tidings tidings) {
        newsService.save(tidings);
        return "redirect:/main/";
    }

    @GetMapping()
    public String mainPage(Model model){
        model.addAttribute("people", personServices.findAll());
        model.addAttribute("tidings", newsService.findAll());
        return "main/main";
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "admin";
    }

}
