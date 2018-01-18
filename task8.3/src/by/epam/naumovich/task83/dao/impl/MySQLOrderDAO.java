package by.epam.naumovich.task83.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.naumovich.task82.dao.pool.MySQLConnectionPool;
import by.epam.naumovich.task82.dao.pool.exception.ConnectionPoolException;
import by.epam.naumovich.task83.dao.IOrderDAO;
import by.epam.naumovich.task83.dao.exception.DAOException;
import by.epam.naumovich.task83.domain.Order;

public class MySQLOrderDAO implements IOrderDAO {

	private static final MySQLOrderDAO instance = new MySQLOrderDAO();
	
	public static final String INSERT_NEW_ORDER = "INSERT INTO Orders (o_user, o_film, o_date, o_paym) VALUES (?, ?, ?, ?)";
	public static final String DELETE_ORDER = "DELETE FROM Orders WHERE o_num = ?";
	public static final String SELECT_ALL_ORDERS = "SELECT * FROM Orders";
	public static final String SELECT_ORDER_BY_USER_ID = "SELECT * FROM Orders WHERE o_user = ?";
	public static final String SELECT_ORDER_BY_FILM_ID = "SELECT * FROM Orders WHERE o_film = ?";
	
	
	public static MySQLOrderDAO getInstance() {
		return instance;
	}

	@Override
	public List<Order> getOrdersByUserId(int id) throws DAOException {
		List<Order> orderList = new ArrayList<Order>();
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(SELECT_ORDER_BY_USER_ID);
			st.setString(1, String.valueOf(id));
			rs = st.executeQuery();
			
			while (rs.next()) {
				Order order = new Order();
				order.setOrdNum(rs.getInt(1));
				order.setUserId(rs.getInt(2));
				order.setFilmId(rs.getInt(3));
				order.setDate(rs.getDate(4));
				order.setPayment(rs.getFloat(5));
	
				orderList.add(order);
			}
			
		} catch (SQLException e) {
			throw new DAOException("Failure during SQL Select Request execution", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Failure during taking connection from ConnectionPool", e);
		} finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				throw new DAOException("Result Set or Statement was not closed properly");
			} finally {
				pool.closeConnection(con);
			}
		}
		return orderList;
	}

	@Override
	public List<Order> getOrdersByFilmId(int id) throws DAOException {
		List<Order> orderList = new ArrayList<Order>();
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(SELECT_ORDER_BY_FILM_ID);
			st.setString(1, String.valueOf(id));
			rs = st.executeQuery();
			
			while (rs.next()) {
				Order order = new Order();
				order.setOrdNum(rs.getInt(1));
				order.setUserId(rs.getInt(2));
				order.setFilmId(rs.getInt(3));
				order.setDate(rs.getDate(4));
				order.setPayment(rs.getFloat(5));
	
				orderList.add(order);
			}
			
		} catch (SQLException e) {
			throw new DAOException("Failure during SQL Select Request execution", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Failure during taking connection from ConnectionPool", e);
		} finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				throw new DAOException("Result Set or Statement was not closed properly");
			} finally {
				pool.closeConnection(con);
			}
		}
		return orderList;
	}

	@Override
	public List<Order> getAllOrders() throws DAOException {
		List<Order> orderList = new ArrayList<Order>();
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(SELECT_ALL_ORDERS);
			rs = st.executeQuery();
			
			while (rs.next()) {
				Order order = new Order();
				order.setOrdNum(rs.getInt(1));
				order.setUserId(rs.getInt(2));
				order.setFilmId(rs.getInt(3));
				order.setDate(rs.getDate(4));
				order.setPayment(rs.getFloat(5));
				
				orderList.add(order);
			}
			
		} catch (SQLException e) {
			throw new DAOException("Failure during SQL Select Request execution", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Failure during taking connection from ConnectionPool", e);
		} finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				throw new DAOException("Result Set or Statement was not closed properly");
			} finally {
				pool.closeConnection(con);
			}
		}
		return orderList;
	}

	@Override
	public void addOrder(Order order) throws DAOException {
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(INSERT_NEW_ORDER);
			st.setInt(1, order.getUserId());
			st.setInt(2, order.getFilmId());
			st.setDate(3, order.getDate());
			st.setFloat(4, order.getPayment());
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DAOException("Failure during SQL Insert Request execution", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Failure during taking connection from ConnectionPool", e);
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DAOException("Prepared Statement was not closed properly");
			} finally {
				pool.closeConnection(con);
			}
		}
	}

	@Override
	public void deleteOrder(Order order) throws DAOException {
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(DELETE_ORDER);
			st.setString(1, String.valueOf(order.getOrdNum()));
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DAOException("Failure during SQL Delete Request execution", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Failure during taking connection from ConnectionPool", e);
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DAOException("Prepared Statement was not closed properly");
			} finally {
				pool.closeConnection(con);
			}
		}
	}
	
	
}
