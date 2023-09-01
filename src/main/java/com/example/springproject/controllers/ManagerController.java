package com.example.springproject.controllers;
import java.io.FileNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.springproject.CompanySingleton;
import com.example.springproject.Exceptions.IdAlreadyValorized;
import com.example.springproject.Exceptions.ManagerNotFound;
import com.example.springproject.Exceptions.PersonNotFound;
import com.example.springproject.Exceptions.TryingAssignEmployeeAsManager;
import com.example.springproject.Model.Manager;

@RestController 
public class ManagerController {    
    @PostMapping("/manager/addManager")  //add manager si confonde con update se non gli passo l'id sennò
    public ResponseEntity<?> addManager(@RequestBody Manager manager,  @RequestParam (value = "idManager", required = false) Integer idManager) throws  ManagerNotFound, TryingAssignEmployeeAsManager, IdAlreadyValorized, FileNotFoundException {
        int value;
        if (idManager != null){
            value = idManager.intValue();
            try{
                CompanySingleton.GetInstance().GetCompanyBL().AddManager(manager, value);
                return new ResponseEntity<>(HttpStatus.OK);
            }catch(IdAlreadyValorized e){
                return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
            }
        }else{
            try{
                CompanySingleton.GetInstance().GetCompanyBL().AddManager(manager);
                return new ResponseEntity<>(HttpStatus.OK);
            }catch(IdAlreadyValorized e){
                return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PutMapping("/manager") 
    public ResponseEntity<?> updatePersons(@RequestBody Manager manager) throws  PersonNotFound, FileNotFoundException {
        try{
            CompanySingleton.GetInstance().GetCompanyBL().ModifyPerson(manager);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(PersonNotFound e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/manager/print/{idManager}") 
    public ResponseEntity<?> printPersons(@PathVariable int idManager) throws  ManagerNotFound, FileNotFoundException {
        try{
            String stringa = CompanySingleton.GetInstance().GetCompanyBL().printManager(idManager);
            return new ResponseEntity<>(stringa, HttpStatus.OK);
        }catch(ManagerNotFound e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
