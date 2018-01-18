package by.epam.naumovich.task83.dao;

import java.util.List;

import by.epam.naumovich.task83.dao.exception.DAOException;
import by.epam.naumovich.task83.domain.News;

public interface INewsDAO {

	void addNews(News news) throws DAOException;
	void deleteNews(News news) throws DAOException;
	List<News> getAllNews() throws DAOException;
	List<News> getNewsByYear(int year) throws DAOException;
	List<News> getNewsByMonth(int month) throws DAOException;

}
