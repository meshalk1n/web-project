package com.example.project.services;

import com.example.project.models.Book;
import com.example.project.models.Person;
import com.example.project.repositories.BookRepository;
import com.example.project.repositories.PersonRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;

    private final BookRepository bookRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, BookRepository bookRepository) {
        this.personRepository = personRepository;
        this.bookRepository = bookRepository;
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Person findOne(int id){
        Optional<Person> foundPerson = personRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person){
        personRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatePerson){
        updatePerson.setId(id);
        personRepository.save(updatePerson);
    }

    @Transactional
    public void delete(int id){
        personRepository.deleteById(id);
    }

    //показывает, сколько взял книг человек
    public List<Book> getBooksByPersonId(int id){
        Optional<Person> person = personRepository.findById(id);

        if(person.isPresent()){
            Hibernate.initialize(person.get().getBooks());
            //книги будут подгружены, но на всякий случай вызвать Hibernate.initialize()
            return person.get().getBooks();
        }
        else {
            return Collections.emptyList();
        }
    }
}