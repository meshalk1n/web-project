package com.example.project.controllers;

import com.example.project.models.Tasks;
import com.example.project.models.Person;
import com.example.project.services.TasksService;
import com.example.project.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TasksController {

    private final TasksService tasksServices;

    private final PersonService personServices;

    @Autowired
    public TasksController(TasksService tasksServices, PersonService personServices) {
        this.tasksServices = tasksServices;
        this.personServices = personServices;
    }


    @GetMapping()
    public String showAllBooks(Model model) {
        model.addAttribute("tasks", tasksServices.findAll());
        return "tasks/tasks";
    }

    @GetMapping("/new")
    public String showFormForNewBook(@ModelAttribute("task") Tasks tasks) {
        return "tasks/new";
    }

    @PostMapping()
    public String processSaveNewBook(@ModelAttribute("task") @Valid Tasks tasks,
                                     BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "tasks/new";
        }
        tasksServices.save(tasks);
        return "redirect:tasks/";
    }

    @GetMapping("/{id}")
    public String processFetchBookById(@PathVariable("id") int id, Model model,
                                       @ModelAttribute("person") Person person) {
        model.addAttribute("task", tasksServices.findOne(id));
        Optional<Person> bookOwner = tasksServices.getBookOwner(id);
        if(bookOwner.isPresent()){
            model.addAttribute("owner", bookOwner.get());
        }else{
            model.addAttribute("people", personServices.findAll());
        }
        return "tasks/id";
    }

    @GetMapping("/{id}/edit")
    public String showFormEdit(Model model, @PathVariable("id") int id) {
        model.addAttribute("task", tasksServices.findOne(id));
        return "tasks/edit";
    }

    @PatchMapping("/{id}")
    public String processEditForm(@PathVariable("id") int id, @ModelAttribute("person") @Valid Tasks tasks,
                                  BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "tasks/edit";
        }
        tasksServices.update(id, tasks);
        return "redirect:/tasks/";
    }

    @DeleteMapping("/{id}")
    public String processDeleteBook(@PathVariable("id") int id) {
        tasksServices.delete(id);
        return "redirect:/tasks/";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        tasksServices.release(id);
        return "redirect:/tasks/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id,
                         @ModelAttribute("person") Person selectedPerson) {
        tasksServices.assign(id, selectedPerson);
        return "redirect:/tasks/" + id;
    }
}
