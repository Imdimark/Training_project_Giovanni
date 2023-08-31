package com.example.springproject.controllers;

import java.io.FileNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springproject.CompanySingleton;
import com.example.springproject.Exceptions.IdAlreadyValorized;
import com.example.springproject.Exceptions.ManagerHasPersonsInList;
import com.example.springproject.Exceptions.ManagerNotFound;
import com.example.springproject.Exceptions.PersonNotFound;
import com.example.springproject.Exceptions.TryingAssignEmployeeAsManager;
import com.example.springproject.Model.Employee;

@RestController 
public class EmployeeController {    

    
    @PostMapping("/employee/{idManager}") 
    public Employee AddPerson(@RequestBody Employee employee, @PathVariable int idManager) throws  ManagerNotFound, TryingAssignEmployeeAsManager, IdAlreadyValorized, FileNotFoundException {
        /*Manager Vincenzo= new Manager("Vincenzo","b","cybersec");*/
        System.out.println("employee" + employee);
        CompanySingleton.GetInstance().GetCompanyBL().AddEmployee(employee, idManager);
       
        return employee;
    }

    @PutMapping("/employee") 
    public Employee updatePerson(@RequestBody Employee employee) throws  ManagerNotFound, TryingAssignEmployeeAsManager, IdAlreadyValorized, FileNotFoundException, PersonNotFound {
        
        //List<Person> myList = CompanySingleton.GetInstance().GetCompanyBL().getPersons();

        CompanySingleton.GetInstance().GetCompanyBL().ModifyPerson(employee);
        /*Manager Vincenzo= new Manager("Vincenzo","b","cybersec");
        myList.add(Vincenzo);*/
        return employee ;
    }

    

}
