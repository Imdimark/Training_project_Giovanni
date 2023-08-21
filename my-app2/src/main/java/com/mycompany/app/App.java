package com.mycompany.app;
import com.mycompany.app.Model.Manager;
import com.mycompany.app.Model.Person;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.File;
import java.io.FileOutputStream;
import com.mycompany.app.Model.Company;
import com.mycompany.app.Model.Employee;
import java.io.IOException;
public class App 
{    
    public static void main( String[] args ) throws JAXBException, IOException 
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
        Manager Pluto= new Manager("Pluto","b","Sviluppo");
        Manager Plutonio = new Manager("Mario","b","cybersec");
        Manager Venere = new Manager("Pluto","b","Sviluppo");




        Employee pippo = new Employee("Pippo", "mario", "devops");    
        Employee Gaetano = new Employee("Gaetano", "mario", "devops");
        Employee Giovanni = new Employee("GIovanni", "mario", "devops");
        Employee Pierluigi = new Employee("Pierluigi", "mario", "devops");
        System.out.println( Vincenzo.FullName() );
        

        System.out.println( Vincenzo.GetPersons() );

        Engineering.AddManager(Vincenzo);
        Engineering.AddManager(Pluto);
        Engineering.AddManager(Mario);
        Engineering.AddEmployee(Gaetano, Vincenzo.GetId());
        Engineering.AddEmployee(pippo, Vincenzo.GetId());       
        Engineering.AddEmployee(Giovanni, Mario.GetId()); 
        
        Engineering.MovePerson(Mario, Vincenzo.GetId());
        Engineering.MovePerson(Pluto, Mario.GetId());
        Engineering.MovePerson(Plutonio, Pluto.GetId());
        Engineering.MovePerson(Venere, Plutonio.GetId());






        //System.out.println( Vincenzo.GetPersons() );        
        /*System.out.println( Gaetano.GetId() );
        System.out.println( Mario.GetId() );
        Engineering.MovePerson(Gaetano, Mario.GetId());
        System.out.println( Vincenzo.GetPersons() );
        System.out.println( Mario.GetPersons() );
        Engineering.DeletePerson(Vincenzo);
        Engineering.ModifyPerson(Pierluigi);*/
   
        
        Engineering.printHierarchy(Vincenzo,0 ); 

        //da spostare in un file diverso
        JAXBContext context = JAXBContext.newInstance(Company.class, Manager.class, Employee.class, Person.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(Engineering, System.out);
        FileOutputStream structure_file = new FileOutputStream("output.xml");
        marshaller.marshal(Engineering, structure_file);
        structure_file.close();


    }

    
    

}
