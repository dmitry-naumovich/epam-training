package by.epam.naumovich.task83.dao;

import java.util.List;

import by.epam.naumovich.task83.dao.exception.DAOException;
import by.epam.naumovich.task83.domain.Order;

public interface IOrderDAO {

	void addOrder(Order order) throws DAOException;
	void deleteOrder(Order order) throws DAOException;
	
	List<Order> getOrdersByUserId(int id) throws DAOException;
	List<Order> getOrdersByFilmId(int id) throws DAOException;
	List<Order> getAllOrders() throws DAOException;
	
}
