package by.epam.naumovich.task82.dao.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.naumovich.task82.dao.pool.exception.ConnectionPoolException;
import resources.DBParameter;

public final class MySQLConnectionPool {

	static final Logger logger = LogManager.getRootLogger();
	
	private static MySQLConnectionPool instance;
	
	private Queue<Connection> freeConnections;
	private Queue<Connection> busyConnections;
	
	private String driverName;
	private String url;
	private String user;
	private String password;
	private int poolSize;
	
	
	public synchronized static MySQLConnectionPool getInstance() throws ConnectionPoolException {
		if (instance == null) {
			instance = new MySQLConnectionPool();
		}
		return instance;
	}
	
	private MySQLConnectionPool() throws ConnectionPoolException {
		freeConnections = new LinkedList<Connection>();
		busyConnections = new LinkedList<Connection>();
		
		DBResourceManager manager = DBResourceManager.getInstance();
		
		this.driverName = manager.getValue(DBParameter.DB_DRIVER);
		this.url = manager.getValue(DBParameter.DB_URL);
		this.user = manager.getValue(DBParameter.DB_USER);
		this.password = manager.getValue(DBParameter.DB_PASSWORD);
		try {
			this.poolSize = Integer.parseInt(manager.getValue(DBParameter.DB_POOL_SIZE));
			Class.forName(driverName);
		} catch (NumberFormatException e) {
			poolSize = 10;
		} catch (ClassNotFoundException e) {
			throw new ConnectionPoolException("SQLException happened in ConnectionPool", e);
		}
	}
	
	public synchronized Connection getConnection() throws ConnectionPoolException {
		if (freeConnections.isEmpty() && busyConnections.isEmpty()) {
			fillEmptyConnectionList();
		}
		else if (freeConnections.isEmpty() && !busyConnections.isEmpty()) {
			addOneNewConnection();
		}
		Connection con = freeConnections.poll();
		busyConnections.add(con);
		return con;
	}
	
	private void fillEmptyConnectionList() throws ConnectionPoolException {
		try {
			Connection con = null;
			for (int i = 0; i < poolSize; i++) {
				con = DriverManager.getConnection(url, user, password);
				freeConnections.add(con);
				
			}
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException happened in ConnectionPool", e);
		}
	}
	
	private void addOneNewConnection() throws ConnectionPoolException  {
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			freeConnections.add(con);
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException happened in ConnectionPool", e);
		}
		
	}

	public void closeConnection(Connection con) {
		try {
			freeConnection(con);
		} catch (SQLException e) {
			logger.error("Connection is not closed and returned to pool", e);
		}
	}
	
	private synchronized void freeConnection(Connection con) throws SQLException {
		if (con.isClosed()) {
			throw new SQLException("Impossible to close closed connection.");
		}
		if (!con.getAutoCommit()) {
			con.commit();
		}
		if (!busyConnections.remove(con)) {
			throw new SQLException("No such connection found in the connection pool");
		}
		
		if (!freeConnections.offer(con)) {
			throw new SQLException("Error allocating connection in the pool. No space currently available");
		}
	}
}