package by.epam.naumovich.task4.service;

import by.epam.naumovich.task4.service.impl.NewsServiceImpl;

public class ServiceFactory {
	
	private static final ServiceFactory factory = new ServiceFactory();
	
	public static ServiceFactory getInstance() {
		return factory;
	}
	
	public INewsService getService() {
		return new NewsServiceImpl();
	}

}
