package com.mycompany.app;

import java.io.File;
import java.io.FileNotFoundException;
import com.mycompany.app.Model.Company;
import com.mycompany.app.Model.Controller;
import com.mycompany.app.Model.Employee;
import com.mycompany.app.Model.Manager;
import com.mycompany.app.Model.Person;

public class Client {

        public static void main( String[] args ) throws FileNotFoundException  
        {
        File file = new File("Company.xml"); 
        
        if(file.exists() ){
            System.out.println(file + " Exists");
            //fail il load del file con jaxb
        }else{
            System.out.println(file + " Does not exists");
            // istanzia qui company e oggetti           
        }

        Company engineering = new Company();
        Controller controller =new Controller(engineering);
        Manager Vincenzo= new Manager("Vincenzo","b","cybersec");
        Manager Mario= new Manager("Mario","b","cybersec");
        Manager Venere = new Manager("Venere","b","Sviluppo");
        Employee pippo = new Employee("Pippo", "mario", "devops");    
        Employee Gaetano = new Employee("Gaetano", "mario", "devops");
        Employee Giovanni = new Employee("GIovanni", "mario", "devops");
        System.out.println( Vincenzo.FullName() );
        System.out.println( Vincenzo.getPersons() );
        //controller.AddManager(Mariolino);
        controller.AddManager(Vincenzo);        
        controller.AddManager(Mario, Vincenzo.getId()); 
        controller.AddManager (Venere, Mario.getId());
         Person gaetano = controller.AddEmployee(Gaetano, Vincenzo.getId());        
        controller.DeletePerson(gaetano.getId());
        controller.AddEmployee(pippo, Vincenzo.getId());       
        controller.AddEmployee(Giovanni, Mario.getId());      
        System.out.println(controller.printManager(Vincenzo.getId() )); 
        controller.test(Venere);
        
        controller.Save();
        
        //System.out.println(out.toString());     
        }

}
