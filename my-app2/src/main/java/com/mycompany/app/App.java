package com.mycompany.app;
import javax.print.attribute.standard.MediaSize.Engineering;
import com.mycompany.app.Model.Manager;
import com.mycompany.app.Model.Person;

import java.io.File;

import com.mycompany.app.Model.Company;
import com.mycompany.app.Model.Employee;



public class App 
{
    
    public static void main( String[] args )
    {

        File file = new File("structure.xml"); 
        
        if(file.exists() ){
            System.out.println(file + " Exists");
        }else{
            System.out.println(file + " Does not exists");
            
        }
        Company Engineering = new Company();
        

        Manager Vincenzo= new Manager("al capone","b","d");
        Employee pippo = new Employee("ciccio", "mario", "devops");
        
        System.out.println( Vincenzo.FullName() );
        

        System.out.println( pippo.GetId() );
        System.out.println( Vincenzo.GetId() );
        Employee Gaetano = new Employee("Gaetano", "mario", "devops");
        Employee Giovanni = new Employee("Gaetano", "mario", "devops");
        Employee Pierluigi = new Employee("Gaetano", "mario", "devops");

        int employeeId = Gaetano.GetId();
        

        System.out.println( Vincenzo.GetPersons() );
        Engineering.AddManager(Vincenzo);
        Engineering.AddEmployee(Gaetano, Vincenzo.GetId());
    }

    
    

}
