package com.mycompany.app.Model;


public abstract class Person implements IPerson {
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
    public int GetId() {
        return id;
    }
}



