package com.example.springproject.Model;
import java.util.ArrayList;
import java.util.List;

public class Manager extends Person {
    
    public Manager() {
        this.persons= new ArrayList();
    }
    
    public Manager(String name, String surname, String department) {        
        super(name,surname);
        this.department = department;
        this.persons= new ArrayList();
    }

    private List<Person> persons;
    private String department;
    public List<Person> getPersons() {
        return this.persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    
    public void AddPersonInList(Person person){
        this.persons.add(person);

    }

    public void RemovePersonInList(Person person){
        this.persons.remove(person);
    }

    @Override
    public String FullName() {
        return String.format("%1$s %2$s (%3$s)", super.name,super.surName,this.department);
    }
  
}
