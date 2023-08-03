package com.mycompany.app;
import com.mycompany.app.Model.Manager;
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
        Manager Mario= new Manager("al capone","b","d");
        Manager Pluto= new Manager("al capone","b","d");
        Employee pippo = new Employee("ciccio", "mario", "devops");    
        Employee Gaetano = new Employee("Gaetano", "mario", "devops");
        Employee Giovanni = new Employee("Gaetano", "mario", "devops");
        Employee Pierluigi = new Employee("Gaetano", "mario", "devops");

        System.out.println( Vincenzo.FullName() );
        

        System.out.println( Vincenzo.GetPersons() );

        Engineering.AddManager(Vincenzo);
        Engineering.AddManager(Pluto);
        Engineering.AddManager(Mario);
        Engineering.AddEmployee(Gaetano, Vincenzo.GetId());
        Engineering.AddEmployee(pippo, Vincenzo.GetId());
        System.out.println( Vincenzo.GetPersons() );
        
        System.out.println( Gaetano.GetId() );
        System.out.println( Mario.GetId() );


        Engineering.MovePerson(Gaetano, Mario.GetId());
        System.out.println( Vincenzo.GetPersons() );
        System.out.println( Mario.GetPersons() );
        Engineering.DeletePerson(Vincenzo);
        Engineering.ModifyPerson(Pierluigi);
    }

    
    

}