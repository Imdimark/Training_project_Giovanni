package com.mycompany.app;

import java.io.FileOutputStream;
import java.io.IOException;



import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

public class SaveXML  {
    public static void saveToXML(Company Engineering) throws IOException {
         JAXBContext context = JAXBContext.newInstance(Company.class, Manager.class, Employee.class, Person.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(Engineering, System.out);
        FileOutputStream structure_file = new FileOutputStream("output.xml");
        marshaller.marshal(Engineering, structure_file);
        structure_file.close();
    }
}
