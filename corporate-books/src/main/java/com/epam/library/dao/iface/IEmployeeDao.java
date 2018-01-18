package com.epam.library.dao.iface;

import java.util.List;

import com.epam.library.dao.exception.DAOException;
import com.epam.library.domain.Employee;

public interface IEmployeeDao {

	void addEmployee(Employee employee) throws DAOException;
	void updateEmployee(int id, Employee updEmployee) throws DAOException;
	void deleteEmployee(int id) throws DAOException;
	Employee getEmployeeById(int id) throws DAOException;
	List<Employee> getAllEmployees() throws DAOException;
}
