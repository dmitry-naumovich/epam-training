package by.epam.naumovich.task4.domain.response;

public class SaveNewsResponse extends Response {

	private static final String RESPONSE_TYPE = "saveNews";

	@Override
	public String getResponseType() {
		return RESPONSE_TYPE;
	}
	
	
}
