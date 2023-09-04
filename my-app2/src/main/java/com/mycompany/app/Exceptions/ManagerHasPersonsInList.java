package com.mycompany.app.Exceptions;

import java.util.List;

import com.mycompany.app.Model.Manager;
import com.mycompany.app.Model.Person;

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
