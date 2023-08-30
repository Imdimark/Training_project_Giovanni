package com.example.springproject.controllers;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springproject.CompanySingleton;
import com.example.springproject.Exceptions.IdAlreadyValorized;
import com.example.springproject.Exceptions.ManagerHasPersonsInList;
import com.example.springproject.Exceptions.ManagerNotFound;
import com.example.springproject.Exceptions.TryingAssignEmployeeAsManager;
import com.example.springproject.Model.Employee;
import com.example.springproject.Model.Manager;
import com.example.springproject.Model.Person;

@RestController 
public class EmployeeController {    

    
    @PostMapping("/company/addEmployee/{idManager}") 
    public Employee AddPerson(@RequestBody Employee employee, @PathVariable int idManager) throws  ManagerNotFound, TryingAssignEmployeeAsManager, IdAlreadyValorized, FileNotFoundException {
        /*Manager Vincenzo= new Manager("Vincenzo","b","cybersec");*/
        System.out.println("employee" + employee);
        CompanySingleton.GetInstance().GetCompanyBL().AddEmployee(employee, idManager);
       
        return employee;
    }

    @PostMapping("/company/updateEmployee/") 
    public List<Person> movePersons(@RequestBody Employee employee) throws  ManagerNotFound, TryingAssignEmployeeAsManager, IdAlreadyValorized, FileNotFoundException {
        
        List<Person> myList = CompanySingleton.GetInstance().GetCompanyBL().getPersons();

        CompanySingleton.GetInstance().GetCompanyBL().MovePerson(idPerson, idManager);
        /*Manager Vincenzo= new Manager("Vincenzo","b","cybersec");
        myList.add(Vincenzo);*/
        return myList ;
    }

    @DeleteMapping("/company/deletePersons/{idPerson}") 
    public List<Person> deletPersons(@PathVariable int idPerson) throws  ManagerNotFound, TryingAssignEmployeeAsManager, IdAlreadyValorized, FileNotFoundException, ManagerHasPersonsInList {
        
        List<Person> myList = CompanySingleton.GetInstance().GetCompanyBL().getPersons();

        CompanySingleton.GetInstance().GetCompanyBL().DeletePerson(idPerson);
        /*Manager Vincenzo= new Manager("Vincenzo","b","cybersec");
        myList.add(Vincenzo);*/
        return myList ;
    }

}
