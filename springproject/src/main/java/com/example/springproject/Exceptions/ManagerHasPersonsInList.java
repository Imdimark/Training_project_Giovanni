package com.example.springproject.Exceptions;

import java.util.List;

import com.example.springproject.Model.Manager;
import com.example.springproject.Model.Person;

public class ManagerHasPersonsInList extends Exception {
    private List<Person> persons;
    
    
    public ManagerHasPersonsInList(Manager manager) {
        
        super("Manager you are trying to delete has these persons to delete before: " + manager.getPersons() );
        this.persons = manager.getPersons(); 
    }

    public List<Person> getPersonUnderManager(){
        return persons;
    }
}
