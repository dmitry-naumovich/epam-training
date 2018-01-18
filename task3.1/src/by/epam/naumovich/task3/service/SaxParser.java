package by.epam.naumovich.task3.service;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.epam.naumovich.task3.domain.MenuItem;

public class SaxParser implements IParser {

	private List<MenuItem> XMLList;
	
	public SaxParser() {
		XMLList = new ArrayList<MenuItem>();
	}
	
	public List<MenuItem> getXMLList() {
		return XMLList;
	}
	
	public void setXMLList(List<MenuItem> xMLList) {
		XMLList = xMLList;
	}
	
	@Override
	public void parse(String fileAddress) throws SAXException {
		XMLReader reader = XMLReaderFactory.createXMLReader();
		MenuSaxHandler handler = new MenuSaxHandler();
		reader.setContentHandler(handler);
		try {
			reader.parse(new InputSource(fileAddress));
		} catch (Exception e) {
			e.printStackTrace();
		}
		XMLList = handler.getItemList();
	}
}
