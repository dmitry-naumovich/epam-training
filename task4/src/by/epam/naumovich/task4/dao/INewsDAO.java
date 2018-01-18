package by.epam.naumovich.task4.dao;

import by.epam.naumovich.task4.dao.exception.DAOException;
import by.epam.naumovich.task4.domain.NewsCatalog;

public interface INewsDAO {

	void saveNews(NewsCatalog catalog) throws DAOException;
	NewsCatalog getNewsCatalog() throws DAOException;
}
