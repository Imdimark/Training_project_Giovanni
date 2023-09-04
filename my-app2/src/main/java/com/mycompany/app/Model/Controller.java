package com.mycompany.app.Model;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class Controller {
    private Company company;
    
    public Controller(Company company) {
        this.company = company;
      }
    
    
    public Manager AddManager(Manager manager){
        
        //company.getPersons().add(manager);
        company.getPersons().add(manager);

        System.out.println( "Ho aggiunto il manager");
        int newId = company.getValorizedId() + 1;
        manager.setId(newId);
        company.setValorizedId(newId);        
        return manager;
    }

    public Manager AddManager(Manager manager_child, int manager){              
        if (manager_child.getId() != null){
            //exception
        }
        Person ReturnedPerson = findPersonById (manager);
        if (ReturnedPerson instanceof Manager){
            Manager manager_father = (Manager)ReturnedPerson;
            manager_father.AddPersonInList(manager_child);
            int newId = company.getValorizedId() + 1;
            manager_child.setId(newId);
            company.setValorizedId(newId);
            return manager_child;
        }
        else{

            System.out.println( "O il manager non esiste oppure stai cercando di assegnare un impiegato come manager");
            return null;        
        }   
    }

    public Person AddEmployee(Employee employee, int manager){     
        if (employee.getId() != null){
            //exception
        }

        Person ReturnedPerson = findPersonById (manager);
        if (ReturnedPerson instanceof Manager){


            Manager Manager = (Manager)ReturnedPerson;
            Manager.AddPersonInList(employee);    
            System.out.println( "ho aggiunto l'employee");
            int newId = company.getValorizedId() +1;
            employee.setId(newId);
            company.setValorizedId(newId);
            return employee;

        }
        
        else{ // ReturnedPerson isistanceof Employee or null 
            System.out.println( "O il manager non esiste oppure stai cercando di assegnare un impiegato come manager");
            return null;        
        }
    }

    private Person AddPerson(Person person, int manager){
        Person ReturnedPerson = findPersonById (manager);
        if (ReturnedPerson == null) {
            System.out.println("Manager non trovato.");
            return null;           
            
        }else{
            if(person instanceof Manager){
                Manager manager_toadd = (Manager) person;
                AddManager(manager_toadd, manager);
                System.out.println( "ho aggiunto");
                /*manager.AddPersonInList(manager_toadd);    
                
                manager_toadd.SetId(valorized_employee_id++);*/
                return manager_toadd;

            }else if(person instanceof Employee){
                Employee employee = (Employee) person;
                /*manager.AddPersonInList(person);    
                
                Employee employee = (Employee)person;
                employee.SetId(valorized_employee_id++);*/
                AddEmployee(employee, manager);
                System.out.println( "ho aggiunto");
                return employee;
            }else {
                System.out.println("Stai provando ad utilizzare il metodo AddPerson con una persona che non è Manager o Employee.");
                return null;
            }
        }
        
    }


    public void DeletePerson(int idperson){ ////////////////// serve un id non una person

        Person person = findPersonById(idperson);
        if (person instanceof Employee){ //la persona che stai cercando di eliminare è un'empoyee           
            Manager manager_from = findSupervisor(person, company.getPersons(),true);
            if (manager_from == null){
                System.out.println( "La persona che stai cercando di eliminare non è stata aggiunta");
                return;
            }else{
                System.out.println( "La persona che stai cercando di eliminare viene dal manager:" + manager_from);
                manager_from.RemovePersonInList(person);

            } 
        }else { //la persona che stai cercando di eliminare è un manager
            boolean hasSupervisor = findSupervisor (person, company.getPersons(),true) != null;
            Manager manager = (Manager)person;
            
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

                System.out.println( "Stai cercando di eliminare un manager che ha delle persone sotto di lui, elimina prima quelle");
            }
        }
    } 

    public void MovePerson(int idperson, int Manager){   
        Person person = findPersonById(idperson);  
        if (person == null){
            //throw new PersonNotFound();
            System.out.println( "Person not found");
        }
        
        Manager manager_from = findSupervisor(person, company.getPersons(),true);
        
        if (manager_from != null){ //se aveva già un manager
            manager_from.RemovePersonInList(person); 
        }
        else{
            company.getPersons().remove(person);
        }
        AddPerson(person, Manager);
        System.out.println("aggiunto");
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
    
    public void test(Person person) {
        Manager person_ = (Manager)person;
        for (Person personn_ : person_.getPersons()){
            System.out.println("Ha sotto di se: " + personn_.FullName());
        }
        
    }
    /*
    public void test2(int id) {
        
        System.out.println(findManagerById(id, persons).FullName() + "TESTTTTTTT sto recuperando manager da id");
    }*/

    private Person findPersonById(int id, List<Person> persons_iteration) {   //////////////////// findpersonbyid ritorna una persona return
        for (Person person_ : persons_iteration) {
            
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


    private Person findPersonById (int id) {
        
        return findPersonById ( id, company.getPersons());

    }


    public void ModifyPerson(Person person) {
        System.out.println( "Sono " + person.name + " e sono una persona con delle proprietà diverse");
    }

    
    private String printHierarchy(Person person, int level) {
        StringBuilder hierarchy = new StringBuilder();
        for (int i = 0; i < level; i++) {
          //System.out.print("\t");
          hierarchy.append("\t");
        }         
        hierarchy.append(person.FullName()).append("\n");
        if (person instanceof Manager) {
          Manager manager = (Manager) person;

          for (Person subordinate : manager.getPersons()) {
            hierarchy.append(printHierarchy(subordinate, level + 1));

          }
        }
        return hierarchy.toString();
    }

    public String printManager(int personid) {
        Person person = findPersonById(personid);

        return printHierarchy(person, 0);
    }

    public Void Save() throws FileNotFoundException {
        XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("Company.xml")));
        //ByteArrayOutputStream out = new ByteArrayOutputStream();
        encoder.writeObject(this.company);
        encoder.close();
        return null;

    }


    
    
}
