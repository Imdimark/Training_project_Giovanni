package com.mycompany.app.Model;
import java.util.ArrayList;
import java.util.List;

public class Manager extends Person {
    private static int counter = 500;
    //List<Person> persons;
    
    public Manager(String name, String surname, String department) {
        super(name,surname);
        this.department = department;
        this.id = ++counter;
        this.persons = new ArrayList<>();
    }
    private String department;
    private List<Person> persons;


    public List<Person> GetPersons()
    {
        return this.persons;
    }

    @Override
    public String FullName() {
        return String.format("%1$s %2$s (%3$s)", super.name,super.surName,this.department);
    }

    public int GetId() {
        return id;
      }

    
    
}
