package com.epam.library.dao.iface;

import java.util.List;

import com.epam.library.dao.exception.DAOException;
import com.epam.library.domain.Book;

public interface IBookDao {

	void addBook(Book book) throws DAOException;
	void updateBook(int id, Book updBook) throws DAOException;
	void deleteBook(int id) throws DAOException;
	Book getBookById(int id) throws DAOException;
	List<Book> getAllBooks() throws DAOException;
	
	void addBookForEmployee(int bookID, int employeeID) throws DAOException;
	List<Book> getAllEmployeeBoks(int employeeID) throws DAOException;
	
	String getReportForMoreThan(int howMany) throws DAOException;
	String getReportLessOrEqualThan(int howMany) throws DAOException;
}
