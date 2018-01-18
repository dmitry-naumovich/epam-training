package by.epam.naumovich.task4.service;


import java.util.List;

import by.epam.naumovich.task4.domain.Category;
import by.epam.naumovich.task4.domain.Criteria;
import by.epam.naumovich.task4.domain.News;
import by.epam.naumovich.task4.domain.NewsCatalog;
import by.epam.naumovich.task4.domain.Subcategory;
import by.epam.naumovich.task4.service.exception.ServiceException;

public interface INewsService {
	void saveNewNews(News news, Category cat, Subcategory subcat) throws ServiceException;
	List<News> findNews(Criteria criteria, List<String> fieldsToSearchBy) throws ServiceException;
	NewsCatalog getNewsCatalog() throws ServiceException;
	void createXMLWithStructure() throws ServiceException;
}
