package com.example.project.repositories;

import com.example.project.models.Tidings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<Tidings, Integer> {
}
