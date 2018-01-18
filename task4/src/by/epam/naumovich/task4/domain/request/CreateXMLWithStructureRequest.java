package by.epam.naumovich.task4.domain.request;

public class CreateXMLWithStructureRequest extends Request {

	private static final String commandName = "CREATE_XML_WITH_STRUCTURE";
	
	@Override
	public String getCommandName() {
		return commandName;
	}

	
}
