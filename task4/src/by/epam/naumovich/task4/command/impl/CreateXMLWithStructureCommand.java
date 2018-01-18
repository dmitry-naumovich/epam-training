package by.epam.naumovich.task4.command.impl;

import by.epam.naumovich.task4.command.Command;
import by.epam.naumovich.task4.domain.request.Request;
import by.epam.naumovich.task4.domain.response.CreateXMLWithStructureResponse;
import by.epam.naumovich.task4.domain.response.Response;
import by.epam.naumovich.task4.service.INewsService;
import by.epam.naumovich.task4.service.ServiceFactory;
import by.epam.naumovich.task4.service.exception.ServiceException;

public class CreateXMLWithStructureCommand implements Command {

	@Override
	public Response execute(Request request) {
		ServiceFactory factory = ServiceFactory.getInstance();
		INewsService service = factory.getService();
		CreateXMLWithStructureResponse response = new CreateXMLWithStructureResponse();
		try {
			service.createXMLWithStructure();
			response.setStatus(true);
			response.setMessage("XML with Structure created");
		} catch (ServiceException e) {
			// logging!!!
			response.setStatus(false);
			response.setMessage("Error! Operation failed.");
		}
		
		return response;
	}

}
