package by.epam.naumovich.task83.dao;

import java.sql.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import by.epam.naumovich.task83.dao.DAOFactory;
import by.epam.naumovich.task83.dao.IOrderDAO;
import by.epam.naumovich.task83.dao.exception.DAOException;
import by.epam.naumovich.task83.domain.Order;

public class IOrderDAOTest{

	@Test
	public List<Order> getOrdersByUserId() throws DAOException {
		// To realize later
		return null;
	}

	@Test
	public List<Order> getOrdersByFilmId() throws DAOException {
		// To realize later
		return null;
	}

	@Test
	public List<Order> getAllOrders() throws DAOException {
		// To realize later
		return null;
	}

	@SuppressWarnings("deprecation")
	@Test
	public void addOrder() throws DAOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory("mysql");
		IOrderDAO orderDAO = daoFactory.getOrderDAO();
		
		Order expectedOrder = new Order();
		expectedOrder.setOrdNum(1);
		expectedOrder.setUserId(4);
		expectedOrder.setFilmId(4);
		expectedOrder.setDate(new Date(2016, 6, 5));
		expectedOrder.setPayment(12f);
		

		orderDAO.addOrder(expectedOrder);
        List<Order> allOrders = orderDAO.getAllOrders();
        Order actualOrder = allOrders.get(allOrders.size() - 1);

        orderDAO.deleteOrder(actualOrder);

        Assert.assertEquals(expectedOrder.getOrdNum(), actualOrder.getOrdNum(), 0.01);
        Assert.assertEquals(expectedOrder.getUserId(), actualOrder.getUserId(), 0.01);
        Assert.assertEquals(expectedOrder.getFilmId(), actualOrder.getFilmId(), 0.01);
        Assert.assertEquals(expectedOrder.getDate().toString(), actualOrder.getDate().toString(), 0.01);
        Assert.assertEquals(expectedOrder.getPayment(), actualOrder.getPayment(), 0.01);
		
	}

	@Test
	public void deleteOrder(Order order) throws DAOException {
		// to realize later
		
	}

}
