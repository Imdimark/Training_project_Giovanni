package com.mycompany.app.Model;
//import com.mycompany.app.Model.App;

import jakarta.xml.bind.annotation.XmlElement;


public class Employee extends Person{
    
    public Employee(String name, String surname, String role) {
        super(name,surname);
        
        this.role = role;
        this.id = 0;
    }
  
    @XmlElement(name = "name")
    protected String name;

    @XmlElement(name = "surname")
    protected String surname;
    
    @XmlElement(name = "role")
    private String role;
    
    public int GetId() {
        return id;
    }

    public void SetId(int idd){
        
        this.id = idd;
        
    }
    
    @Override
    public String FullName() {
        return String.format("%1$s %2$s (%3$s)", super.name,super.surName,this.role);
    }


    public void printEmployee(int level) {
        for (int i = 0; i < level; i++) {
          System.out.print("\t");
        }
        System.out.println("Employee: " + name);
      }
    

}
