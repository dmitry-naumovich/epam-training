package com.epam.library.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.library.dao.DBParameter;
import com.epam.library.dao.DBResourceManager;
import com.epam.library.dao.ExceptionMessages;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.dao.iface.IEmployeeDao;
import com.epam.library.domain.Employee;

public class EmployeeDaoImpl implements IEmployeeDao {

	public static final String INSERT_NEW_EMPLOYEE = "INSERT INTO employee (name, date_of_birth, email) VALUES (?, ?, ?)";
	public static final String UPDATE_EMPLOYEE = "UPDATE employee SET name = ?, date_of_birth = ?, email = ? WHERE id = ?";
	public static final String DELETE_EMPLOYEE = "DELETE FROM employee WHERE id = ?";
	public static final String SELECT_EMPLOYEE_BY_ID = "SELECT * FROM employee WHERE id = ?";
	public static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM employee";
	
	private String driverName;
	private String url;
	private String user;
	private String password;
	
	public void init() throws ClassNotFoundException {
		DBResourceManager manager = DBResourceManager.getInstance();
		this.driverName = manager.getValue(DBParameter.DB_DRIVER);
		this.url = manager.getValue(DBParameter.DB_URL);
		this.user = manager.getValue(DBParameter.DB_USER);
		this.password = manager.getValue(DBParameter.DB_PASSWORD);
		Class.forName(driverName);
	}
	
	@Override
	public void addEmployee(Employee employee) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement(INSERT_NEW_EMPLOYEE);
			st.setString(1, employee.getName());
			st.setDate(2,  employee.getBirthDate());
			st.setString(3, employee.getEmail());
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(ExceptionMessages.SQL_INSERT_FAILURE, e);
		} finally {
			try {
				if (st != null) { st.close(); }
			} catch (SQLException e) {
				throw new DAOException(ExceptionMessages.PREP_STATEMENT_NOT_CLOSED, e);
			}
		}
	}

	@Override
	public void updateEmployee(int id, Employee updEmployee) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement(UPDATE_EMPLOYEE);
			st.setString(1, updEmployee.getName());
			st.setDate(2, updEmployee.getBirthDate());
			st.setString(3, updEmployee.getEmail());
			st.setInt(4, id);
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DAOException(ExceptionMessages.CONNECTION_NOT_TAKEN, e);
		} finally {
			try {
				if (st != null) { st.close(); }
			} catch (SQLException e) {
				throw new DAOException(ExceptionMessages.PREP_STATEMENT_NOT_CLOSED, e);
			}
		}
	}

	@Override
	public void deleteEmployee(int id) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement(DELETE_EMPLOYEE);
			st.setInt(1, id);
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DAOException(ExceptionMessages.SQL_DELETE_FAILURE, e);
		} finally {
			try {
				if (st != null) { st.close(); }
			} catch (SQLException e) {
				throw new DAOException(ExceptionMessages.PREP_STATEMENT_NOT_CLOSED, e);
			}
		}
	}

	@Override
	public Employee getEmployeeById(int id) throws DAOException {
		Employee employee = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement(SELECT_EMPLOYEE_BY_ID);
			st.setInt(1, id);
			rs = st.executeQuery();
			
			while (rs.next()) {
				employee = new Employee();
				employee.setId(rs.getInt(1));
				employee.setName(rs.getString(2));
				employee.setBirthDate(rs.getDate(3));
				employee.setEmail(rs.getString(4));
			}
			
		} catch (SQLException e) {
			throw new DAOException(ExceptionMessages.SQL_SELECT_FAILURE, e);
		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (st != null) { st.close(); }
			} catch (SQLException e) {
				throw new DAOException(ExceptionMessages.RS_OR_STATEMENT_NOT_CLOSED);
			}
			
		}
		return employee;
	}

	@Override
	public List<Employee> getAllEmployees() throws DAOException {
		List<Employee> employees = new ArrayList<>();
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement(SELECT_ALL_EMPLOYEES);
			rs = st.executeQuery();
			
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setId(rs.getInt(1));
				employee.setName(rs.getString(2));
				employee.setBirthDate(rs.getDate(3));
				employee.setEmail(rs.getString(4));
				employees.add(employee);
			}
			
		} catch (SQLException e) {
			throw new DAOException(ExceptionMessages.SQL_SELECT_FAILURE, e);
		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (st != null) { st.close(); }
			} catch (SQLException e) {
				throw new DAOException(ExceptionMessages.RS_OR_STATEMENT_NOT_CLOSED);
			}
			
		}
		return employees;
	}

}
