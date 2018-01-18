package by.epam.naumovich.task3.service;

public class ParserFactory {

	private static final ParserFactory factory = new ParserFactory();
	
	private enum ParserType {
		SAX, STAX
	}
	
	public static ParserFactory getFactory() {
		return factory;
	}

	public IParser getParser(String parserType) {
		ParserType pType = ParserType.valueOf(parserType.toUpperCase());
		switch (pType) {
		case SAX:
			return new SaxParser();
		case STAX:
			return new StaxParser();
		default:
			throw new EnumConstantNotPresentException(pType.getDeclaringClass(), pType.name());
		}
	}
}
