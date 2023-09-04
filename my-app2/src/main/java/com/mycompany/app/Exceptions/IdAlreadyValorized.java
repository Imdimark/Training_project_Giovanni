package com.example.springproject.Exceptions;

import com.example.springproject.Model.Person;

public class IdAlreadyValorized extends Exception {
    private Person person;
    
    
    public IdAlreadyValorized(Person person) {
        
        super("This person is already valorized: " + person.FullName() );
        this.person = person; 
    }

    public Person getId(){
        return person;
    }
}
