package by.epam.naumovich.task83.main;

import java.util.ArrayList;
import java.util.List;

import by.epam.naumovich.task83.dao.DAOFactory;
import by.epam.naumovich.task83.dao.IFilmDAO;
import by.epam.naumovich.task83.dao.IUserDAO;
import by.epam.naumovich.task83.dao.exception.DAOException;
import by.epam.naumovich.task83.dao.impl.MySQLDAOFactory;
import by.epam.naumovich.task83.domain.Film;
import by.epam.naumovich.task83.domain.User;

public class Main {

	public static void main(String[] args) throws DAOException {
		DAOFactory daoFactory = MySQLDAOFactory.getDAOFactory("mysql");
		IUserDAO daoUser = daoFactory.getUserDAO();
		IFilmDAO daoFilm = daoFactory.getFilmDAO();
		User u1 = null;
		List<User> u2 = new ArrayList<User>();
		
		List<Film> f1 = new ArrayList<Film>();
		List<Film> f2 = new ArrayList<Film>();
		try {
			
			u1 = daoUser.getUserByLogin("lola-iam");
			u2 = daoUser.getUsersInBan();
			f1 = daoFilm.getFilmsByName("Lucy");
			f2 = daoFilm.getFilmsByName("Apollo 13");
		} catch (DAOException e) {
			e.printStackTrace();
		}
		System.out.println(u1 + "\n" + u2);
		System.out.println(f1 + "\n" + f2);
	}

}
