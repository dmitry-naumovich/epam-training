package by.epam.naumovich.task4.command.impl;

import by.epam.naumovich.task4.command.Command;
import by.epam.naumovich.task4.domain.request.Request;
import by.epam.naumovich.task4.domain.request.SaveNewNewsRequest;
import by.epam.naumovich.task4.domain.response.Response;
import by.epam.naumovich.task4.domain.response.SaveNewsResponse;
import by.epam.naumovich.task4.service.INewsService;
import by.epam.naumovich.task4.service.ServiceFactory;
import by.epam.naumovich.task4.service.exception.ServiceException;

public class SaveNewNewsCommand implements Command {
	
	@Override
	public Response execute(Request request) {
		
		ServiceFactory factory = ServiceFactory.getInstance();
		INewsService service = factory.getService();
		SaveNewsResponse response = new SaveNewsResponse();
		try {
			SaveNewNewsRequest req = (SaveNewNewsRequest)request;
			service.saveNewNews(req.getNewsToSave(), req.getCategory(), req.getSubcategory());
			response.setStatus(true);
			response.setMessage("New news saved successfully");
		} catch (ServiceException e) {
			// logging
			response.setStatus(false);
			response.setMessage("Error! Operation failed.");
		}
		
		return response;
	}

}
