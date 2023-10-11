package com.example.springproject.controllers;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springproject.CompanySingleton;
import com.example.springproject.Exceptions.IdAlreadyValorized;
import com.example.springproject.Exceptions.ManagerNotFound;
import com.example.springproject.Exceptions.PersonNotFound;
import com.example.springproject.Exceptions.TryingAssignEmployeeAsManager;
import com.example.springproject.Model.Employee;

@RestController 
public class EmployeeController {    
    @PostMapping("/employee/{idManager}") 
    public ResponseEntity<?> addPerson(@RequestBody Employee employee, @PathVariable int idManager) throws FileNotFoundException, IdAlreadyValorized, TryingAssignEmployeeAsManager, ManagerNotFound  {
        try{
            CompanySingleton.GetInstance().GetCompanyBL().AddEmployee(employee, idManager);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(ManagerNotFound e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch(TryingAssignEmployeeAsManager e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(IdAlreadyValorized e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/employee") 
    public ResponseEntity<?> updatePerson(@RequestBody Employee employee) throws  ManagerNotFound, TryingAssignEmployeeAsManager, IdAlreadyValorized, FileNotFoundException, PersonNotFound {
         try{
            CompanySingleton.GetInstance().GetCompanyBL().ModifyPerson(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(PersonNotFound e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }       
    }
}
