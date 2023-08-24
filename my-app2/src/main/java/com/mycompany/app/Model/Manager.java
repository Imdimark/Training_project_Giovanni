package com.mycompany.app.Model;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;



public class Manager extends Person {
    private static int counter = 500;
    public Manager(String name, String surname, String department) {
        
        super(name,surname);
        this.department = department;
        this.id = ++counter;
        this.persons = new ArrayList<>();
    }
    
    
    @XmlElement(name = "name")
    protected String name;

    @XmlElement(name = "surname")
    protected String surname;
    
    public Manager() {
    }
    

    @XmlElement(name = "person")
    private List<Person> persons;

    @XmlElement(name = "department")
    private String department;

    public void SetId(int idd){        
        this.id = idd;        
    }

    public void AddPersonInList(Person person){
        this.persons.add(person);

    }

    public void RemovePersonInList(Person person){
        this.persons.remove(person);
    }

    public List<Person> GetPersons()
    {
        return this.persons;
    }

    @Override
    public String FullName() {
        return String.format("%1$s %2$s (%3$s)", super.name,super.surName,this.department);
    }


  
}
