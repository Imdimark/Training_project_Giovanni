package com.example.springproject.Model;

public class Employee extends Person{
    
    public Employee(String name, String surname, String role) {
        super(name,surname);        
        this.role = role;
    }
    public Employee() {
    }
    protected String name;
    protected String surname;
    private String role;
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    @Override
    public String FullName() {
        return String.format("%1$s %2$s (%3$s)", super.name,super.surName,this.role);
    }

}
