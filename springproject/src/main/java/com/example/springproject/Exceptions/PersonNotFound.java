package com.example.springproject.Exceptions;

import com.example.springproject.Model.Person;

public class PersonNotFound extends Exception{
    private Person person;
    
    
    public PersonNotFound(Person person) {
        
        super("Person with this id: " + person + "cannot be find");
        this.person = person; 
    }

    public Person getId(){
        return person;
    }
}
