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

    @GetMapping("tidings/tidings")
    public String showFormForNewBook(@ModelAttribute("tidings") Tidings tidings) {
        return "tidings/tidings";
    }


    @GetMapping("tidings/{id}")
    public String processFetchPersonById(@PathVariable("id") int id, Model model) {
        model.addAttribute("tiding", newsService.findOne(id));
        return "tidings/id";
    }

    @GetMapping("tidings/{id}/edit")
    public String showFormEdit(Model model, @PathVariable("id") int id) {
        model.addAttribute("tiding", newsService.findOne(id));
        return "tidings/edit";
    }

    @PatchMapping("tidings/{id}")
    public String processEditForm(@PathVariable("id") int id, @ModelAttribute("tiding") @Valid Tidings tidings) {
        newsService.update(id, tidings);
        return "redirect:/main/";
    }

    @DeleteMapping("tidings/{id}")
    public String processDeletePerson(@PathVariable("id") int id) {
        newsService.delete(id);
        return "redirect:/main/";
    }
}
