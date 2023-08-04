package com.mycompany.app.Model;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;

//@XmlRootElement(name = "manager")

public class Manager extends Person {
    private static int counter = 500;
    //List<Person> persons;
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
        // Costruttore senza argomenti
    }
    @XmlElement(name = "department")
    private String department;

    @XmlElement(name = "person")
    private List<Person> persons;

    public void SetId(int idd){        
        this.id = idd;        
    }

    /*public void AddEmployeeInList(Employee employee){
        this.persons.add(employee);

    }*/

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

    public int GetId() {
        return id;
    }

    public void printManager(int level) {
        for (int i = 0; i < level; i++) {
          System.out.print("\t");
        }        
        System.out.println("Manager: " + name);
        for (Object subordinate : persons) {
          if (subordinate instanceof Manager) {
            ((Manager) subordinate).printManager(level + 1);
          } else if (subordinate instanceof Employee) {
            ((Employee) subordinate).printEmployee(level + 1);
          }
        }  
      }
  
}
