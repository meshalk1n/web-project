package com.example.project.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "это поле не должно быть пустым")
    @Column(name = "username")
    private String username;

    @Min(value = 1, message = "это поле должно быть больше 0")
    @Column(name = "age")
    private int age;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "owner")
    private List<Tasks> tasks;

    public Person(){}

    public Person(String username, int age, String password) {
        this.username = username;
        this.age = age;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Tasks> getBooks() {
        return tasks;
    }

    public void setBooks(List<Tasks> tasks) {
        this.tasks = tasks;
    }
}