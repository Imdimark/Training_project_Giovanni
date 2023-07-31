package com.mycompany.app;

import java.io.File;

public class Client {
    
    public static void main(String[] args) {

        File file = new File("structure.xml"); 
        
        if(file.exists() ){
            System.out.println(file + " Exists");
        }else{
            System.out.println(file + " Does not exists");
            
        }

        }


        private Company company;
        public Client(Company company) {
            this.company = company;
        }
    
        public void addEmployee(Person person) {
            company.addEmployee(person);
        }



}
