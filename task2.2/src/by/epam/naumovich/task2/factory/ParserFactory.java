package by.epam.naumovich.task2.factory;

import by.epam.naumovich.task2.service.IParser;
import by.epam.naumovich.task2.service.MyParser;

public class ParserFactory {

	private static final ParserFactory factory = new ParserFactory();
	
	public static ParserFactory getFactory() {
		return factory;
	}

	public IParser getParser() {
		return new MyParser();
	}
}
