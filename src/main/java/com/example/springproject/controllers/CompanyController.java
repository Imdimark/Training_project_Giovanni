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
import com.example.springproject.Model.Manager;
import com.example.springproject.Model.Person;

@RestController 
public class CompanyController {    

    @GetMapping("/company/getPersons")
    public List<Person> getPersonsFromCompany() throws  ManagerNotFound, TryingAssignEmployeeAsManager, IdAlreadyValorized, FileNotFoundException {
        
        List<Person> myList = CompanySingleton.GetInstance().GetCompanyBL().getPersons();
        /*Manager Vincenzo= new Manager("Vincenzo","b","cybersec");
        myList.add(Vincenzo);*/
        return myList ;
    }
    @PostMapping("/company/addPersons") //primo livello
    public List<Person> AddPerson(@RequestBody Manager manager) throws  ManagerNotFound, TryingAssignEmployeeAsManager, IdAlreadyValorized, FileNotFoundException {
        /*Manager Vincenzo= new Manager("Vincenzo","b","cybersec");*/
        System.out.println("managerrrrr" + manager);
        CompanySingleton.GetInstance().GetCompanyBL().AddManager(manager);

        List<Person> myList = CompanySingleton.GetInstance().GetCompanyBL().getPersons();
       
        return myList ;
    }

    @PutMapping("/company/movePersons/{idPerson}/{idManager}") 
    public List<Person> movePersons(@PathVariable int idPerson, @PathVariable int idManager) throws  ManagerNotFound, TryingAssignEmployeeAsManager, IdAlreadyValorized, FileNotFoundException {
        
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
