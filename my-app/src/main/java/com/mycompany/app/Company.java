package com.mycompany.app;
import java.util.ArrayList;
import java.util.List;





public class Company {
    private List<Person> employees;

    public Company() {
        this.employees = new ArrayList<>();
    }
    
    
    public void addEmployee(Person person) {
        this.employees.add(person);
    }

    public void removeEmployee(Person person) {
        this.employees.remove(person);
    }

    public List<Person> getEmployees() {
        return new ArrayList<>(employees); // Ritorna una copia per proteggere l'incapsulamento
    }
    
}
