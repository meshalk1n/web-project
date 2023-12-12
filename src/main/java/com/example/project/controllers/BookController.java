package com.example.project.controllers;

import com.example.project.models.Book;
import com.example.project.models.Person;
import com.example.project.services.BookService;
import com.example.project.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookServices;

    private final PersonService personServices;

    @Autowired
    public BookController(BookService bookServices, PersonService personServices) {
        this.bookServices = bookServices;
        this.personServices = personServices;
    }

    @GetMapping()
    public String showAllBooks(Model model) {
        model.addAttribute("books", bookServices.findAll());
        return "books/show";
    }

    @GetMapping("/new")
    public String showFormForNewBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String processSaveNewBook(@ModelAttribute("book") @Valid Book book,
                                     BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "books/new";
        }
        bookServices.save(book);
        return "redirect:/books/";
    }

    @GetMapping("/{id}")
    public String processFetchBookById(@PathVariable("id") int id, Model model,
                                       @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookServices.findOne(id));
        Optional<Person> bookOwner = bookServices.getBookOwner(id);
        if(bookOwner.isPresent()){
            model.addAttribute("owner", bookOwner.get());
        }else{
            model.addAttribute("people", personServices.findAll());
        }
        return "books/id";
    }

    @GetMapping("/{id}/edit")
    public String showFormEdit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookServices.findOne(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String processEditForm(@PathVariable("id") int id, @ModelAttribute("person") @Valid Book book,
                                  BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "books/edit";
        }
        bookServices.update(id, book);
        return "redirect:/books/";
    }

    @DeleteMapping("/{id}")
    public String processDeleteBook(@PathVariable("id") int id) {
        bookServices.delete(id);
        return "redirect:/books/";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        bookServices.release(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id,
                         @ModelAttribute("person") Person selectedPerson) {
        bookServices.assign(id, selectedPerson);
        return "redirect:/books/" + id;
    }
}