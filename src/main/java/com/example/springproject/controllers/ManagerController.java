package com.example.springproject.controllers;
import java.io.FileNotFoundException;
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
import com.example.springproject.Exceptions.PersonNotFound;
import com.example.springproject.Exceptions.TryingAssignEmployeeAsManager;
import com.example.springproject.Model.Manager;

@RestController 
public class ManagerController {    

    
    @PostMapping("/manager/{idManager}") //modificare
    public Manager AddPerson(@RequestBody Manager manager, @PathVariable int idManager) throws  ManagerNotFound, TryingAssignEmployeeAsManager, IdAlreadyValorized, FileNotFoundException {
        /*Manager Vincenzo= new Manager("Vincenzo","b","cybersec");*/
        System.out.println("Manager" + manager);
        CompanySingleton.GetInstance().GetCompanyBL().AddManager(manager, idManager);
       
        return manager;
    }
    @PostMapping("/manager/addManager/") 
    public Manager AddPerson(@RequestBody Manager manager) throws  ManagerNotFound, TryingAssignEmployeeAsManager, IdAlreadyValorized, FileNotFoundException {
        /*Manager Vincenzo= new Manager("Vincenzo","b","cybersec");*/
        System.out.println("Manager" + manager);
        CompanySingleton.GetInstance().GetCompanyBL().AddManager(manager);
       
        return manager;
    }

    @PutMapping("/manager") 
    public Manager updatePersons(@RequestBody Manager manager) throws  ManagerNotFound, TryingAssignEmployeeAsManager, IdAlreadyValorized, FileNotFoundException, PersonNotFound {
        CompanySingleton.GetInstance().GetCompanyBL().ModifyPerson(manager);
        return manager ;
    }


    @GetMapping("/manager/print/{idManager}") 
    public String printPersons(@PathVariable int idManager) throws  ManagerNotFound, TryingAssignEmployeeAsManager, IdAlreadyValorized, FileNotFoundException, ManagerHasPersonsInList {

        String stringa = CompanySingleton.GetInstance().GetCompanyBL().printManager(idManager);
        return stringa;
    }

}
