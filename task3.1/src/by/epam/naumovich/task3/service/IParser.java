package by.epam.naumovich.task3.service;

import java.io.FileNotFoundException;
import java.util.List;

import org.xml.sax.SAXException;

import by.epam.naumovich.task3.domain.MenuItem;

public interface IParser {

	void parse(String fileAddress) throws SAXException, FileNotFoundException;
	
	List<MenuItem> getXMLList();
}
