package com.mycompany.app.Model;

import java.io.Serializable;

public abstract class Person implements IPerson, Serializable {
    public Person(String name, String surname) {
        super();
        this.name = name;
        this.surName = surname;
    }
    protected Integer id;
    protected String name;
    protected String surName;


    public Person() { //per Jabxc
        
    }
     public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }
}



