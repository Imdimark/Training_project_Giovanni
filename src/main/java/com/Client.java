package com;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.example.springproject.BusinnesLogic.CompanyBL;
import com.example.springproject.Exceptions.IdAlreadyValorized;
import com.example.springproject.Exceptions.ManagerHasPersonsInList;
import com.example.springproject.Exceptions.ManagerNotFound;
import com.example.springproject.Exceptions.TryingAssignEmployeeAsManager;
import com.example.springproject.Model.Company;
import com.example.springproject.Model.Employee;
import com.example.springproject.Model.Manager;

public class Client {
        public static void main( String[] args ) throws FileNotFoundException, IdAlreadyValorized, ManagerNotFound, TryingAssignEmployeeAsManager, ManagerHasPersonsInList  
        {
            File file = new File("Company.xml"); 
            Company engineering;
            CompanyBL controller = null;;
            if(file.exists() ){
                System.out.println(file + " Exists");
                XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("Company.xml")));
                engineering = (Company)decoder.readObject();
                decoder.close();
                controller =new CompanyBL(engineering);

            }else{
                System.out.println(file + " Does not exists");
                engineering = new Company();                    
                controller =new CompanyBL(engineering);
                Manager Vincenzo= new Manager("Vincenzo","b","cybersec");
                Manager Mario= new Manager("Mario","b","cybersec");
                Manager Venere = new Manager("Venere","b","Sviluppo");
                Employee pippo = new Employee("Pippo", "mario", "devops");    
                Employee Gaetano = new Employee("Gaetano", "mario", "devops");
                Employee Giovanni = new Employee("GIovanni", "mario", "devops");
                System.out.println( Vincenzo.FullName() );
                System.out.println( Vincenzo.getPersons() );
                controller.AddManager(Vincenzo); 
                controller.AddManager(Mario, Vincenzo.getId()); 
                controller.AddManager (Venere, Mario.getId());
                controller.AddEmployee(Gaetano, Vincenzo.getId());        
                controller.AddEmployee(Giovanni, Mario.getId()); 
                controller.AddEmployee(pippo, Vincenzo.getId()); 
                System.out.println(controller.printManager(Vincenzo.getId() ));   
                controller.Save();         
            }                      
        }
}
