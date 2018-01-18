package by.epam.naumovich.task4.command.impl;

import java.util.List;

import by.epam.naumovich.task4.command.Command;
import by.epam.naumovich.task4.domain.News;
import by.epam.naumovich.task4.domain.request.FindNewsRequest;
import by.epam.naumovich.task4.domain.request.Request;
import by.epam.naumovich.task4.domain.response.FindNewsResponse;
import by.epam.naumovich.task4.domain.response.Response;
import by.epam.naumovich.task4.service.INewsService;
import by.epam.naumovich.task4.service.ServiceFactory;
import by.epam.naumovich.task4.service.exception.ServiceException;

public class FindNewsCommand implements Command {

	@Override
	public Response execute(Request request) {
		ServiceFactory factory = ServiceFactory.getInstance();
		INewsService service = factory.getService();
		FindNewsResponse response = new FindNewsResponse();
		try {
			FindNewsRequest req = (FindNewsRequest)request;
			List<News> news = service.findNews(req.getCriteria(), req.getFieldsSearchBy());
			response.setNews(news);
			response.setStatus(true);
			response.setMessage("News was/were found");
		} catch (ServiceException e) {
			// logging
			response.setStatus(false);
			response.setMessage("Error! Operation failed.");
		}
		
		return response;
	}

}
