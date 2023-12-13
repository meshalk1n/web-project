package com.example.project.controllers;

import com.example.project.models.Person;
import com.example.project.services.TasksService;
import com.example.project.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/teams/")
public class TeamsController {


    private final PersonService personServices;

    private final TasksService tasksServices;

    @Autowired
    public TeamsController(PersonService personServices, TasksService tasksServices) {
        this.personServices = personServices;
        this.tasksServices = tasksServices;
    }

    @GetMapping()
    public String showAllPeople(Model model) {
        model.addAttribute("people", personServices.findAll());
        return "teams/show";
    }

    @GetMapping("/new")
    public String showFormForNewPerson(@ModelAttribute("person") Person person) {
        return "teams/new";
    }

    @PostMapping()
    public String processSaveNewPerson(@ModelAttribute("person") @Valid Person person,
                                       BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "teams/new";
        }
        personServices.save(person);
        return "redirect:/teams/";
    }

    @GetMapping("/{id}")
    public String processFetchPersonById(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personServices.findOne(id));
        model.addAttribute("tasks", personServices.getBooksByPersonId(id));
        return "teams/id";
    }

    @GetMapping("/{id}/edit")
    public String showFormEdit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personServices.findOne(id));
        return "teams/edit";
    }

    @PatchMapping("/{id}")
    public String processEditForm(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person,
                                  BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "teams/edit";
        }
        personServices.update(id, person);
        return "redirect:/teams/";
    }

    @DeleteMapping("/{id}")
    public String processDeletePerson(@PathVariable("id") int id) {
        personServices.delete(id);
        return "redirect:/teams/";
    }
}
