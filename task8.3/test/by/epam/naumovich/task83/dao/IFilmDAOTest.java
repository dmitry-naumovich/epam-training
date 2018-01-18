package by.epam.naumovich.task83.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import by.epam.naumovich.task83.dao.DAOFactory;
import by.epam.naumovich.task83.dao.IFilmDAO;
import by.epam.naumovich.task83.dao.exception.DAOException;
import by.epam.naumovich.task83.domain.Film;

public class IFilmDAOTest {

	@Test
	public void addNewFilm() throws DAOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory("mysql");
		IFilmDAO filmDAO = daoFactory.getFilmDAO();
		
		Film expectedFilm = new Film();
		expectedFilm.setId(1);
		expectedFilm.setName("Test film name");
		expectedFilm.setYear(2016);
		expectedFilm.setDirector("Test film director");
		expectedFilm.setCountry("France");
		expectedFilm.setGenre("Thriller");
		expectedFilm.setLength(500);
		expectedFilm.setPrice(12f);
		expectedFilm.setRating(4f);
		

		filmDAO.addNewFilm(expectedFilm);
        List<Film> allFilms = filmDAO.getAllFilms();
        Film actualFilm = allFilms.get(allFilms.size() - 1);

        filmDAO.deleteFilm(actualFilm);

        Assert.assertEquals(expectedFilm.getId(), actualFilm.getId(), 0.01);
        Assert.assertEquals(expectedFilm.getName(), actualFilm.getName(), 0.01);
        Assert.assertEquals(expectedFilm.getYear(), actualFilm.getYear(), 0.01);
        Assert.assertEquals(expectedFilm.getDirector(), actualFilm.getDirector(), 0.01);
        Assert.assertEquals(expectedFilm.getCountry(), actualFilm.getCountry(), 0.01);
        Assert.assertEquals(expectedFilm.getGenre(), actualFilm.getGenre(), 0.01);
        Assert.assertEquals(expectedFilm.getLength(), actualFilm.getLength(), 0.01);
        Assert.assertEquals(expectedFilm.getPrice(), actualFilm.getPrice(), 0.01);
        Assert.assertEquals(expectedFilm.getRating(), actualFilm.getRating(), 0.01);
	}

	@Test
	public void deleteFilm() throws DAOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory("mysql");
		IFilmDAO filmDAO = daoFactory.getFilmDAO();

        Film rollbackFilm = filmDAO.getFilmsByName("Cloud Atlas").get(0);
        filmDAO.deleteFilm(rollbackFilm);
        Film deletedFilm = filmDAO.getFilmsByName("Cloud Atlas").get(0);
        filmDAO.addNewFilm(rollbackFilm);

        Assert.assertNull(deletedFilm);
	}

	@Test
	public List<Film> getAllFilms() throws DAOException {
		// to realize later
		return null;
	}

	@Test
	public List<Film> getFilmsByName() throws DAOException {
		// to realize later
		return null;
	}

}
