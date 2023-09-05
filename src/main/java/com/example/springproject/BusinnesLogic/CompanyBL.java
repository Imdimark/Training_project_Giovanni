package com.example.springproject.BusinnesLogic;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.example.springproject.Exceptions.HierarchicalRicursiveLoop;
import com.example.springproject.Exceptions.IdAlreadyValorized;
import com.example.springproject.Exceptions.ManagerHasPersonsInList;
import com.example.springproject.Exceptions.ManagerNotFound;
import com.example.springproject.Exceptions.PersonNotFound;
import com.example.springproject.Exceptions.TryingAssignEmployeeAsManager;
import com.example.springproject.Model.Company;
import com.example.springproject.Model.Employee;
import com.example.springproject.Model.Manager;
import com.example.springproject.Model.Person;

public class CompanyBL {
    private Company company;
    
    public CompanyBL(Company company) {
        this.company = company;
      }
    
    public Manager AddManager(Manager manager) throws IdAlreadyValorized{
        IdAlreadyValorized IdExc = new IdAlreadyValorized((Manager)manager);
        if (manager.getId() != null){
            throw IdExc;
        }
        company.getPersons().add(manager);
        System.out.println( "Ho aggiunto il manager");
        int newId = company.getValorizedId() + 1;
        manager.setId(newId);
        company.setValorizedId(newId);        
        return manager;
    }

    public Manager AddManager(Manager manager_child, int manager) throws IdAlreadyValorized, ManagerNotFound, TryingAssignEmployeeAsManager, HierarchicalRicursiveLoop{              
        IdAlreadyValorized IdExc = new IdAlreadyValorized((Manager)manager_child);
        ManagerNotFound ManNF = new ManagerNotFound(manager);        
        TryingAssignEmployeeAsManager EmployeeAsManager = new TryingAssignEmployeeAsManager(manager);        
        if (manager_child.getId() != null){ // stiamo provando ad aggiungere un manager child che già esiste
            throw IdExc;
        }
        Person ReturnedPerson = findPersonById (manager);        
        if (ReturnedPerson == null){ // stiamo provando ad aggiungere un manager ad un manager che non esiste
            throw ManNF;
        }else{
            if (ReturnedPerson instanceof Manager){
                Manager manager_father = (Manager)ReturnedPerson;
                manager_father.AddPersonInList(manager_child);
                int newId = company.getValorizedId() + 1;
                manager_child.setId(newId);
                company.setValorizedId(newId);
                return manager_child;
            }
            else{
                throw EmployeeAsManager;
            }
        }
    }

    public Employee AddEmployee(Employee employee, int manager) throws IdAlreadyValorized, TryingAssignEmployeeAsManager, ManagerNotFound{     
        IdAlreadyValorized IdExc = new IdAlreadyValorized((Employee)employee);
        ManagerNotFound ManNF = new ManagerNotFound(manager);        
        TryingAssignEmployeeAsManager EmployeeAsManager = new TryingAssignEmployeeAsManager(manager);
        if (employee.getId() != null){
            throw IdExc;
        }
        Person ReturnedPerson = findPersonById (manager);
        if(ReturnedPerson == null){ //Il manager passato come parametro non è nella struttura
            throw ManNF;
        }else{
            if (ReturnedPerson instanceof Manager){
                Manager Manager = (Manager)ReturnedPerson;
                Manager.AddPersonInList(employee);    
                System.out.println( "ho aggiunto l'employee");
                int newId = company.getValorizedId() +1;
                employee.setId(newId);
                company.setValorizedId(newId); 
                System.out.println("entro qui");
                return employee;
            }
            else{ //Impossibile assegnare un manager che sia di tipo Employee
                throw EmployeeAsManager; 
            }
        }
    }

    private Person AddPerson(Person person, int manager) throws IdAlreadyValorized, ManagerNotFound, TryingAssignEmployeeAsManager, HierarchicalRicursiveLoop, PersonNotFound{
        Person ReturnedPerson = findPersonById (manager);
        PersonNotFound PersonNF = new PersonNotFound(person); 
        if (ReturnedPerson == null){
            throw PersonNF;
        }else{
            if(person instanceof Manager){
                Manager manager_toadd = (Manager) person;
                AddManager(manager_toadd, manager);
                System.out.println( "ho aggiunto");
                return manager_toadd;
            }else if(person instanceof Employee){
                Employee employee = (Employee) person;
                AddEmployee(employee, manager);
                System.out.println( "ho aggiunto");
                return employee;
            }else {
                System.out.println("Stai provando ad utilizzare il metodo AddPerson con una persona che non è Manager o Employee.");
                return null;
            }
        }
    }

    public void DeletePerson(int idperson) throws ManagerHasPersonsInList, PersonNotFound{ ////////////////// serve un id non una person
        Person person = findPersonById(idperson);
        PersonNotFound PersonNF = new PersonNotFound(person); 
        if (person == null){
            throw PersonNF;
        }
        if (person instanceof Employee){ //la persona che stai cercando di eliminare è un'empoyee           
            Manager manager_from = findSupervisor(person, company.getPersons(),true);
             
            if (manager_from == null){
                PersonNotFound Person2NF= new PersonNotFound(manager_from);
                throw Person2NF;
            }else{
                System.out.println( "La persona che stai cercando di eliminare viene dal manager:" + manager_from);
                manager_from.RemovePersonInList(person);
            } 
        }else { //la persona che stai cercando di eliminare è un manager
            boolean hasSupervisor = findSupervisor (person, company.getPersons(),true) != null;
            Manager manager = (Manager)person;
            ManagerHasPersonsInList ManHasPerInList = new ManagerHasPersonsInList(manager);
            if (manager.getPersons().isEmpty()){ // persona che è manager e non ha persone sotto di lui
                System.out.println( "Ho dimostrato che è empty");
                if (hasSupervisor){
                    Manager manager_from = findSupervisor(person, company.getPersons(),true);
                    manager_from.RemovePersonInList(person);
                    company.getPersons().remove(person);
                    System.out.println( "Ho dimostrato che ha supervisor");
                }else{
                    company.getPersons().remove(person);
                    System.out.println( "Ho dimostrato che non ha supervisor");
                }
                
            }else{ // persona che è manager, ha persone sotto di lui
                throw ManHasPerInList;
            }
        }
    } 

    public void MovePerson(int idperson, int Manager) throws ManagerHasPersonsInList, PersonNotFound, IdAlreadyValorized, ManagerNotFound, TryingAssignEmployeeAsManager, HierarchicalRicursiveLoop {   
        Person person = findPersonById(idperson);  
        /*HierarchicalRicursiveLoop HierarchicalLoop = new HierarchicalRicursiveLoop( (Manager)person);
        if (findHiterativeLoop(getPersons(), (Manager)person, manager_from)){
            throw HierarchicalLoop;
        }*/

        DeletePerson(idperson);
        int tmp = person.getId();
        person.setId(null);
        
        AddPerson(person, Manager);
        company.setValorizedId(company.getValorizedId() -1);
        person.setId(tmp);
        System.out.println("La persona è stata mossa");
    } 

    private Manager findSupervisor(Person person, List<Person> personss, boolean FirstIteration) { 
        if (personss.contains(person) && FirstIteration){ // Manager under the CEO
                FirstIteration = false;
                return null;
            }       
        for (Person person_ : personss) {            
            if (person_ instanceof Manager){
                Manager manager = (Manager) person_;                
                if (manager.getPersons().contains(person)){
                    return manager;
                }
                else{
                    Manager foundManager = findSupervisor(person, manager.getPersons(), false);
                    if (foundManager != null) {
                        return foundManager;
                    }
                }                 
            }    
        }                     
        return null;
    }
    
    private Person findPersonById(int id, List<Person> personsIteration) {   
        for (Person person_ : personsIteration) {
            if (person_.getId() == id){
                    return person_;
                }
            if (person_ instanceof Manager){
                        Person person= findPersonById(id, ((Manager)person_).getPersons()); 
                        if(person!=null)
                        {
                            return person;
                        }
            }    
        }
        return null;
    }
    
    private Person findPersonById (int id)  {       
        return findPersonById ( id, company.getPersons());        
    }

    private Boolean findHiterativeLoop(List<Person> persons, Manager manager, Manager managerToAdd) {   
        while(persons.contains(manager)){
            if (managerToAdd == manager){
                return true;
            }
            manager = findSupervisor(managerToAdd, persons, false);
        }
        return false;
    }


    
    public void ModifyPerson(Person person) throws PersonNotFound {
        Person personToModify = findPersonById(person.getId()); 
        if(personToModify == null){
            PersonNotFound PersonNF = new PersonNotFound(personToModify); 
            throw PersonNF;
        }
        personToModify.setName(person.getName());
        personToModify.setSurName(person.getSurName());
        if (personToModify instanceof Manager){
            ((Manager)personToModify).setDepartment(((Manager)person).getDepartment());
        }else if (personToModify instanceof Employee){
            ((Employee)personToModify).setRole(null);
        }
    }
    
    private String printHierarchy(Person person, int level, StringBuilder hierarchy) {
        for (int i = 0; i < level; i++) {
          //System.out.print("\t");
          hierarchy.append("\t");
        }         
        hierarchy.append(person.FullName()).append("\n");
        if (person instanceof Manager) {
          Manager manager = (Manager) person;
          for (Person subordinate : manager.getPersons()) {
            printHierarchy(subordinate, level + 1, hierarchy);
          }
        }
        return hierarchy.toString();
    }

    public String printManager(int personid) throws ManagerNotFound { 
        ManagerNotFound ManNF = new ManagerNotFound(personid);
        Person person = findPersonById(personid);
        if(person == null){
            throw ManNF;
        } 
        StringBuilder hierarchy = new StringBuilder();
        return printHierarchy(person, 0, hierarchy);
    }

    public Void Save() throws FileNotFoundException {
        XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("Company.xml")));
        encoder.writeObject(this.company);
        encoder.close();
        return null;
    } 

    public List<Person> getPersons(){
        return company.getPersons();
    }
}
