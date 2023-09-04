package com.mycompany.app.Exceptions;

import com.mycompany.app.Model.Person;

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
