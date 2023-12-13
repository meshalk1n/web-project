package com.example.project.services;

import com.example.project.models.Tasks;
import com.example.project.models.Person;
import com.example.project.repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TasksService {

    private final TasksRepository tasksRepository;


    @Autowired
    public TasksService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public List<Tasks> findAll(){
        return tasksRepository.findAll();
    }

    public Tasks findOne(int id){
        Optional<Tasks> foundBook = tasksRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Tasks tasks){
        tasksRepository.save(tasks);
    }

    @Transactional
    public void update(int id, Tasks updateTasks){
        updateTasks.setId(id);
        tasksRepository.save(updateTasks);
    }

    @Transactional
    public void delete(int id){
        tasksRepository.deleteById(id);
    }

    //получаем владельца книги, если он есть
    public Optional<Person> getBookOwner (int id){
        return tasksRepository.findById(id).map(Tasks::getOwner);
    }

    //освобождает книгу
    @Transactional
    public void release (int id){
        Tasks releasedTasks = tasksRepository.findById(id).get();
        releasedTasks.setOwner(null);
        tasksRepository.save(releasedTasks);
    }

    //назначает книгу
    @Transactional
    public void assign (int id, Person selectedPerson){
        Tasks tasks = tasksRepository.findById(id).get();
        tasks.setOwner(selectedPerson);
        tasksRepository.save(tasks);
    }
}