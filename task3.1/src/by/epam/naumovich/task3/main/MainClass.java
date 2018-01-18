package by.epam.naumovich.task3.main;

import java.io.FileNotFoundException;
import java.util.List;

import org.xml.sax.SAXException;

import by.epam.naumovich.task3.domain.MenuItem;
import by.epam.naumovich.task3.service.IParser;
import by.epam.naumovich.task3.service.ParserFactory;

public class MainClass {
	
	public static final String FILE_ADDRESS = "src/resources/menuXML.xml";
	public static final String SAX_PARSER = "sax";
	public static final String STAX_PARSER = "stax";
	public static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws FileNotFoundException, SAXException {	
		
		ParserFactory factory = ParserFactory.getFactory();
		parseAndPrint(SAX_PARSER, factory);
		parseAndPrint(STAX_PARSER, factory);
		
	}
	
	private static void parseAndPrint(String parserType, ParserFactory factory) throws FileNotFoundException, SAXException {
		IParser parser = factory.getParser(parserType);
		parser.parse(FILE_ADDRESS);
		List<MenuItem> list = parser.getXMLList();
		printMenuList(list);
	}
	
	private static void printMenuList(List<MenuItem> list) {
		for (MenuItem item : list) {
			System.out.println(item + NEW_LINE);
		}
	}
}
