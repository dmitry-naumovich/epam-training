package by.epam.naumovich.task4.dao;

import by.epam.naumovich.task4.dao.impl.NewsDAOImpl;

public class DAOFactory {

	private static final DAOFactory factory = new DAOFactory();
	
	public static DAOFactory getInstance() {
		return factory;
	}
	
	public INewsDAO getNewsDAO() {
		return new NewsDAOImpl();
	}
}
