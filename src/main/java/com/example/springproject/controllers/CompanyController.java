package com.example.springproject.controllers;
import java.io.FileNotFoundException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springproject.CompanySingleton;
import com.example.springproject.Exceptions.IdAlreadyValorized;
import com.example.springproject.Exceptions.ManagerHasPersonsInList;
import com.example.springproject.Exceptions.ManagerNotFound;
import com.example.springproject.Exceptions.PersonNotFound;
import com.example.springproject.Exceptions.TryingAssignEmployeeAsManager;
import com.example.springproject.Model.Person;

@RestController 
public class CompanyController {    

    @GetMapping("/company/getPersons")
    public List<Person> getPersonsFromCompany() throws  ManagerNotFound, TryingAssignEmployeeAsManager, IdAlreadyValorized, FileNotFoundException {
        List<Person> persons = CompanySingleton.GetInstance().GetCompanyBL().getPersons();
        return persons ;
    }

    @PutMapping("/company/movePersons/{idPerson}/{idManager}") 
    public ResponseEntity<?> movePersons(@PathVariable int idPerson, @PathVariable int idManager) throws  ManagerNotFound, TryingAssignEmployeeAsManager, IdAlreadyValorized, FileNotFoundException {
        try{
        CompanySingleton.GetInstance().GetCompanyBL().MovePerson(idPerson, idManager);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(ManagerNotFound e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch(TryingAssignEmployeeAsManager e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/company/deletePersons/{idPerson}") 
    public ResponseEntity<?> deletPersons(@PathVariable int idPerson) throws FileNotFoundException    {
        try{
        CompanySingleton.GetInstance().GetCompanyBL().DeletePerson(idPerson);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(PersonNotFound e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch(ManagerHasPersonsInList e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    } 

    @PostMapping("/company/save") 
    public void Save() throws FileNotFoundException    {
        CompanySingleton.GetInstance().GetCompanyBL().Save();
    }                                      
}
