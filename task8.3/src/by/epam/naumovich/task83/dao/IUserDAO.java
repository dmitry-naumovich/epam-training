package by.epam.naumovich.task83.dao;

import java.util.List;

import by.epam.naumovich.task83.dao.exception.DAOException;
import by.epam.naumovich.task83.domain.User;

public interface IUserDAO {

	void addUser(User user) throws DAOException;
	void deleteUser(User user) throws DAOException;
	
	List<User> getAllUsers() throws DAOException;
	List<User> getUsersInBan() throws DAOException;
	
	User getUserByLogin(String login) throws DAOException;
	
	boolean userIsInBan(int id) throws DAOException;
	void updateUser(int id, User updatedUser) throws DAOException;
	String getPasswordByLogin(String login) throws DAOException;
	
}
