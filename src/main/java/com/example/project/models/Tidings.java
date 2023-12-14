package com.example.project.models;

import javax.persistence.*;

@Entity
@Table(name = "tidings")
public class Tidings {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(name = "name")
    private String name;


    @Column(name = "core")
    private String core;

    public Tidings(){}

    public Tidings(String name, String core) {
        this.name = name;
        this.core = core;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }

    @Override
    public String toString() {
        return "Tidings{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", core='" + core + '\'' +
                '}';
    }
}
