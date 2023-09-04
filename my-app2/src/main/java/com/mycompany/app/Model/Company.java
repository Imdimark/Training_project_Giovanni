package com.mycompany.app.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Company implements Serializable{    
    private List<Person> persons;
    private int valorized_id = 0;
    
    
    public Company() {        
        this.persons = new ArrayList<>();
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public int getValorizedId() {
        return valorized_id;
    }

    public void setValorizedId(int valorized_id) {
        this.valorized_id = valorized_id;
    }

    /*public List<Person> GetPersons()
    {
        return this.persons;
    }
    public void SetPersons(List<Person> persons)
    {
        this.persons = persons;
    }
    private String test;
    
    public String gettest(){
        return this.test;
    }
    public void settest(String value){
        test = value;

    }

*/


    
}





