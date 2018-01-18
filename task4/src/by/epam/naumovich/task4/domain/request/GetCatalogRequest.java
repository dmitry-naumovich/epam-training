package by.epam.naumovich.task4.domain.request;

public class GetCatalogRequest extends Request {

	private static final String commandName = "GET_CATALOG";
	
	@Override
	public String getCommandName() {
		return commandName;
	}

}
