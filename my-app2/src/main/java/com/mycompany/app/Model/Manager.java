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


    public String PrintManager(){
        String stringa_ = "pippo";
        /* restituiscimi la lista di persone che stanno sotto
        for x in list
            
            if manager
                ci stanno persone sotto
                    append
            



        */
        
        System.out.print("└─ " + GetPersons());
        return stringa_;
    }

    
    
}
