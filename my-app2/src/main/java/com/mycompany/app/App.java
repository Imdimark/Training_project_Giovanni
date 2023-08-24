package com.mycompany.app;
import com.mycompany.app.Model.Manager;
import com.mycompany.app.Model.Person;
import com.sun.security.ntlm.Client;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.mycompany.app.Model.Company;
import com.mycompany.app.Model.Employee;
import java.io.IOException;
public class App 



//app java fa un init oload del client
{    
    public static void main( String[] args ) throws FileNotFoundException  
    {
        File file = new File("output.xml"); 
        
        if(file.exists() ){
            System.out.println(file + " Exists");
            //fail il load del file con jaxb
        }else{
            System.out.println(file + " Does not exists");
            // istanzia qui company e oggetti           
        }

        Company Engineering = new Company();
        
        Manager Vincenzo= new Manager("Vincenzo","b","cybersec");
        Manager Mario= new Manager("Mario","b","cybersec");
        Manager Venere = new Manager("Venere","b","Sviluppo");

        Employee pippo = new Employee("Pippo", "mario", "devops");    
        Employee Gaetano = new Employee("Gaetano", "mario", "devops");
        Employee Giovanni = new Employee("GIovanni", "mario", "devops");
        Employee Pierluigi = new Employee("Pierluigi", "mario", "devops");
        System.out.println( Vincenzo.FullName() );
        

        System.out.println( Vincenzo.GetPersons() );

        Engineering.AddManager(Vincenzo);
        

        Engineering.AddManager(Mario, Vincenzo.GetId()); 
        Engineering.AddManager (Venere, Mario.GetId());

         Person gaetano = Engineering.AddEmployee(Gaetano, Vincenzo.GetId());
        
         Engineering.DeletePerson(gaetano.GetId());

        Engineering.AddEmployee(pippo, Vincenzo.GetId());       
        Engineering.AddEmployee(Giovanni, Mario.GetId());
        Engineering.printHierarchy(Vincenzo.GetId() ); 
        Engineering.test(Venere);
        
        XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("Company.xml")));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
 	    //XMLEncoder encoder = new XMLEncoder(out);
	    encoder.writeObject(Engineering);
	    encoder.close();
	    System.out.println(out.toString());

        


    }

    
    

}
