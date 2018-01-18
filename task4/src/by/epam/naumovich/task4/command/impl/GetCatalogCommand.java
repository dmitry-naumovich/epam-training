package by.epam.naumovich.task4.command.impl;

import by.epam.naumovich.task4.command.Command;
import by.epam.naumovich.task4.domain.NewsCatalog;
import by.epam.naumovich.task4.domain.request.Request;
import by.epam.naumovich.task4.domain.response.GetCatalogResponse;
import by.epam.naumovich.task4.domain.response.Response;
import by.epam.naumovich.task4.service.INewsService;
import by.epam.naumovich.task4.service.ServiceFactory;
import by.epam.naumovich.task4.service.exception.ServiceException;

public class GetCatalogCommand implements Command {

	@Override
	public Response execute(Request request) {
		ServiceFactory factory = ServiceFactory.getInstance();
		INewsService service = factory.getService();
		GetCatalogResponse response = new GetCatalogResponse();
		try {
			NewsCatalog cat = service.getNewsCatalog();
			response.setCatalog(cat);
			response.setStatus(true);
			response.setMessage("Catalog returned successfully");
		} catch (ServiceException e) {
			// logging
			response.setStatus(false);
			response.setMessage("Error! Command <getCatalog> failed.");
		}
		
		return response;
	}

}
