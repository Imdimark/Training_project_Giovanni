package com.mycompany.app.Model;
//import com.mycompany.app.Model.App;


public class Employee extends Person{
    private static int counter = 0;
    public Employee(String name, String surname, String role) {
        super(name,surname);
        this.role = role;
        this.id = ++counter;
    }
    private String role;
    
    public int GetId() {
        return id;
      }

    /*public String GetPersons()
    {
        return this.role;
    }*/

    @Override
    public String FullName() {
        return String.format("%1$s %2$s (%3$s)", super.name,super.surName,this.role);
    }
}
