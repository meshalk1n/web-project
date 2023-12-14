package com.example.project.services;

import com.example.project.models.Tidings;
import com.example.project.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {
    private final NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<Tidings> findAll(){
        return newsRepository.findAll();
    }

    public Tidings findOne(int id){
        Optional<Tidings> foundPerson = newsRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Tidings tidings){
        newsRepository.save(tidings);
    }

    @Transactional
    public void update(int id, Tidings updateTidings){
        updateTidings.setId(id);
        newsRepository.save(updateTidings);
    }

    public void delete(int id){
        newsRepository.deleteById(id);
    }
}
