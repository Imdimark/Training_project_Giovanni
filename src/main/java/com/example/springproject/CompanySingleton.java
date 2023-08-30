package com.example.springproject;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.example.springproject.BusinnesLogic.CompanyBL;
import com.example.springproject.Model.Company;

public class CompanySingleton {
    private CompanySingleton() {
        super();
    }

    private static CompanySingleton _instance;

    private CompanyBL companyBL;

    public CompanyBL GetCompanyBL()
    {
        return companyBL;
    }

    public static CompanySingleton GetInstance() throws FileNotFoundException
    {
        if(_instance == null)
        {
            File file = new File("Company.xml"); 
            _instance  = new CompanySingleton();
            Company company = null;
            //aggiungere logica load from xml
            if(file.exists()) {
                System.out.println(file + " Exists");
                try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("Company.xml")))) {
                    company = (Company) decoder.readObject();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    company = new Company(); 
                }
            }else{
                company = new Company();
            }


            
            _instance.companyBL = new CompanyBL(company);
        }
        return _instance;
    }
    
}
