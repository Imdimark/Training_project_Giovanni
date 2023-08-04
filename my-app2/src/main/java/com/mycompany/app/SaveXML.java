package com.mycompany.app;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mycompany.app.Model.Company;

public class SaveXML {
    public static void saveToXML(Company company, String path) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        File file = new File(path);
        xmlMapper.writeValue(file, company);
    }
}
