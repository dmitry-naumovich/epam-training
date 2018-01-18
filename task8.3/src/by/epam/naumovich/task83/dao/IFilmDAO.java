package by.epam.naumovich.task83.dao;

import java.util.List;

import by.epam.naumovich.task83.dao.exception.DAOException;
import by.epam.naumovich.task83.domain.Film;

public interface IFilmDAO {

	void addNewFilm(Film film) throws DAOException;
	void deleteFilm(Film film) throws DAOException;
	
	List<Film> getTwelveLastAddedFilms() throws DAOException;
	List<Film> getAllFilms() throws DAOException;
	
	List<Film> getFilmsByName(String name) throws DAOException;
	List<Film> getFilmsByYear(int year) throws DAOException;
	List<Film> getFilmsByGenre(String genre) throws DAOException;
	
	List<Film> getFilmsByNameYear(String name, int year) throws DAOException;
	List<Film> getFilmsByNameGenre(String name, String genre) throws DAOException;
	List<Film> getFilmsByYearGenre(int year, String genre) throws DAOException;
	
	List<Film> getFilmsByNameYearGenre(String name, int year, String genre) throws DAOException;
	
}
