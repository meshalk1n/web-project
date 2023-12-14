package com.example.project.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "Tasks")
public class Tasks {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "это поле не должно быть пустым")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "это поле не должно быть пустым")
    @Column(name = "description")
    private String description;

    @Column(name = "data")
    private String data;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;
    public Tasks(){}

    public Tasks(String title, String description, String data) {
        this.title = title;
        this.description = description;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String author) {
        this.description = author;
    }

    public String getData() {
        return data;
    }

    public void setData(String year) {
        this.data = year;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Tasks{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + description + '\'' +
                ", year=" + data +
                '}';
    }
}