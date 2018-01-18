package by.epam.naumovich.task83.dao;

import by.epam.naumovich.task83.dao.exception.DAOException;
import by.epam.naumovich.task83.dao.impl.MySQLDAOFactory;

public abstract class DAOFactory {

	public abstract IUserDAO getUserDAO();
	public abstract IFilmDAO getFilmDAO();
	public abstract IOrderDAO getOrderDAO();
	public abstract IReviewDAO getReviewDAO();
	public abstract INewsDAO getNewsDAO();
	
	private enum DBType {
		MYSQL
	}
	
	public static DAOFactory getDAOFactory(String type) throws DAOException { 
		DBType dbType = DBType.valueOf(type.toUpperCase());
		switch(dbType) {
			case MYSQL: 
				return MySQLDAOFactory.getInstance();							
			default : 
				throw new DAOException("Unknown database type.");
		}
	}
}
