package by.epam.naumovich.task4.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import by.epam.naumovich.task4.domain.NewsCatalog;

public class JAXBXMLHandler {

	public static void marshal(NewsCatalog catalog, String fileName) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(NewsCatalog.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        try {
        	m.marshal(catalog, new FileOutputStream(new File(fileName)));
		} catch (FileNotFoundException e) {
			throw new JAXBException("File was not found", e);
		}
    }
	
    public static NewsCatalog unmarshal(String fileName) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(NewsCatalog.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        NewsCatalog catalog = (NewsCatalog) unmarshaller.unmarshal(new File(fileName));
        return catalog;
    }
}
