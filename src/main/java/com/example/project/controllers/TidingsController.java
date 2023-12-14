package com.example.project.controllers;

import com.example.project.models.Tidings;
import com.example.project.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class TidingsController {

    private final NewsService newsService;

    @Autowired
    public TidingsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("main/main/")
    public String showAllBooks(Model model) {
        model.addAttribute("tidings", newsService.findAll());
        return "main/main";
    }

    @GetMapping("tidings/new")
    public String showFormForNewBook(@ModelAttribute("tidings") Tidings tidings) {
        return "tidings/new";
    }

    @PostMapping("/main/")
    public String processSaveNewBook(@ModelAttribute("tidings") @Valid Tidings tidings,
                                     BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "main/main";
        }
        newsService.save(tidings);
        return "redirect:main/";
    }
}
