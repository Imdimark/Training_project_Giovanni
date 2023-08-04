package com.mycompany.app.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "company")
public class Company {
    @XmlElement(name = "manager")
    public List<Person> persons;
    private int valorized_employee_id = 0;
    private int valorized_manager_id = 500;
    
    public Company() {
        
        this.persons = new ArrayList<>();
    }

    
    public Manager AddManager(Manager manager){
        persons.add(manager);
        
        System.out.println( "Ho aggiunto il manager");
        manager.SetId(valorized_manager_id++);
        return manager;
    }

    public Person AddEmployee(Employee employee, int Manager){     
        Manager manager = findManagerById(Manager);
        if (manager == null){
            System.out.println( "O il manager non esiste oppure stai cercando di assegnare un impiegato come manager");
            return null;
        
        }else{
        
            manager.AddPersonInList(employee);    
            System.out.println( "ho aggiunto l'employee");
            employee.SetId(valorized_employee_id++);
            return employee;
        }
    }

    public Person AddPerson(Person person, int Manager){
        Manager manager = findManagerById(Manager);
        if (manager == null) {
            System.out.println("Manager non trovato.");
            return null;           
            
        }else{
            if(person instanceof Manager){
                Manager manager_toadd = (Manager) person;
                manager.AddPersonInList(manager_toadd);    
                System.out.println( "ho aggiunto");
                manager_toadd.SetId(valorized_employee_id++);
                return manager_toadd;

            }else if(person instanceof Employee){
                //Employee employee = (Employee) person;
                manager.AddPersonInList(person);    
                System.out.println( "ho aggiunto");
                Employee employee = (Employee)person;
                employee.SetId(valorized_employee_id++);
                return employee;
            }else {
                System.out.println("Stai provando ad utilizzare il metodo AddPerson con una persona che non è Manager o Employee.");
                return null;
            }
        }
        
    }


    public void DeletePerson(Person person){
        if (person instanceof Employee){ //la persona che stai cercando di eliminare è un'empoyee           
            Manager manager_from = findSupervisor(person);
            if (manager_from == null){
                System.out.println( "La persona che stai cercando di eliminarenon è stata aggiunta");
                return;
            }else{
                 System.out.println( "La persona che stai cercando di eliminare viene dal manager:" + manager_from);
            manager_from.RemovePersonInList(person);

            } 
        }else { //la persona che stai cercando di eliminare r
            boolean hasSupervisor = findSupervisor (person) != null;
            Manager manager = (Manager)person;
            
            if (manager.GetPersons().isEmpty()){ // persona che è manager e non ha persone sotto di lui
                System.out.println( "Ho dimostrato che è empty");
                if (hasSupervisor){
                    Manager manager_from = findSupervisor(person);
                    manager_from.RemovePersonInList(person);
                    persons.remove(person);
                    System.out.println( "Ho dimostrato che ha supervisor");
                }else{
                    persons.remove(person);
                    System.out.println( "Ho dimostrato che non ha supervisor");
                }
                
            }else{ // persona che è manager, ha persone sotto di lui
                System.out.println( "Stai cercando di eliminare un manager che ha delle persone sotto di lui, elimina prima quelle");
            }
        }
    } 

    public void MovePerson(Person person, int Manager){        
        Manager manager_from = findSupervisor(person);
        if (manager_from != null){ //se aveva già un manager
            manager_from.RemovePersonInList(person); 
        }
        AddPerson(person, Manager);
        System.out.println("aggiunto");
    } 
  
    public Manager findSupervisor(Person person) {
        for (Person person_ : persons) {
            Manager manager = (Manager) person_;
            if (manager.GetPersons().contains(person)) {
                return manager;
            }            
        }
        return null;
    }
    
    public Manager findManagerById(int id) {
        for (Person person : persons) {
            System.out.println("Person in the loop" + person);
            if (person instanceof Manager){
                Manager manager = (Manager) person;                
                if (manager.GetId() == id) {
                    return manager;
                }
            }           
        }
        return null; // Restituisci null se l'ID non viene trovato
    }

    public void ModifyPerson(Person person) {
        System.out.println( "Sono " + person.name + " e sono una persona con delle proprietà diverse");
    }

    
    /*public static void printHierarchy(Person person, int level) {
        
        for (int i = 0; i < level; i++) {
          System.out.print("\t");
        }        
        System.out.println(person.FullName()); 
        if (person instanceof Manager) {
          Manager manager = (Manager) person;

          for (Person subordinate : manager.GetPersons()) {
            printHierarchy(subordinate, level + 1);
          }
        }
      }*/





}





