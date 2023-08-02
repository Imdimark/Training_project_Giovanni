package com.mycompany.app.Model;

import java.util.ArrayList;
import java.util.List;

public class Company {
    
    public List<Person> persons;
    private int nextEmployeeId = 1;
    
    public Company() {
        
        this.persons = new ArrayList<>();
    }

    public void AddManager(Manager manager){
        persons.add(manager);
        
        System.out.println( "Ho aggiunto il manager");
    }


    public void AddEmployee(Employee employee, int Manager){
        //manager.persons.add(employee);
        //Engineering.AddEmployee(employee);
        
        System.out.println( "ho aggiunto");
    }

    

}





