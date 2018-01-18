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
import com.epam.library.dao.iface.IBookDao;
import com.epam.library.domain.Book;



public class BookDaoImpl implements IBookDao {

	public static final String INSERT_NEW_BOOK = "INSERT INTO books (title, brief,publish_year,author) VALUES (?, ?, ?, ?)";
	public static final String UPDATE_BOOK = "UPDATE books SET title = ?, brief = ?, publish_year = ?, author = ? WHERE id = ?";
	public static final String DELETE_BOOK = "DELETE FROM books WHERE id = ?";
	public static final String SELECT_BOOK_BY_ID = "SELECT * FROM books WHERE id = ?";
	public static final String SELECT_ALL_BOOKS = "SELECT * FROM books";
	
	public static final String SELECT_EMPS_READ_MORE_THAN = "SELECT employee.name, a.amount FROM (SELECT employee_id as id, count(*) as amount FROM employee_book GROUP BY employee_id) a JOIN employee ON employee.id = a.id WHERE a.amount > ? ORDER BY a.amount DESC";
	public static final String SELECT_EMPS_READ_LESS_OR_EQUALS = "SELECT employee.name, employee.date_of_birth, a.amount FROM (SELECT employee_id as id, count(*) as amount FROM employee_book GROUP BY employee_id) a JOIN employee ON employee.id = a.id WHERE a.amount <= ? ORDER BY employee.date_of_birth ASC, a.amount DESC";
	public static final String INSERT_EMPLOYEE_BOOK = "INSERT INTO employee_book (book_id, employee_id) VALUES (?, ?)";
	public static final String SELECT_BOOKS_BY_EMPLOYEE = "SELECT books.id, books.title, books.brief, books.publish_year, books.author FROM books JOIN employee_book ON books.id = employee_book.book_id WHERE employee_id = ?";
	
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
	public void addBook(Book book) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement(INSERT_NEW_BOOK);
			st.setString(1, book.getTitle());
			st.setString(2,  book.getBrief());
			st.setInt(3, book.getYear());
			st.setString(4, book.getAuthor());
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
	public void updateBook(int id, Book updBook) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement(UPDATE_BOOK);
			st.setString(1, updBook.getTitle());
			st.setString(2, updBook.getBrief());
			st.setInt(3, updBook.getYear());
			st.setString(4, updBook.getAuthor());
			st.setInt(5, id);
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
	public void deleteBook(int id) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement(DELETE_BOOK);
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
	public Book getBookById(int id) throws DAOException {
		Book book = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement(SELECT_BOOK_BY_ID);
			st.setInt(1, id);
			rs = st.executeQuery();
			
			while (rs.next()) {
				book = new Book();
				book.setId(rs.getInt(1));
				book.setTitle(rs.getString(2));
				book.setBrief(rs.getString(3));
				book.setYear((short)rs.getInt(4));
				book.setAuthor(rs.getString(5));
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
		return book;
	}

	@Override
	public List<Book> getAllBooks() throws DAOException {
		List<Book> books = new ArrayList<>();
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement(SELECT_ALL_BOOKS);
			rs = st.executeQuery();
			
			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt(1));
				book.setTitle(rs.getString(2));
				book.setBrief(rs.getString(3));
				book.setYear((short)rs.getInt(4));
				book.setAuthor(rs.getString(5));
				books.add(book);
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
		return books;
	}

	@Override
	public void addBookForEmployee(int bookID, int employeeID) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement(INSERT_EMPLOYEE_BOOK);
			st.setInt(1, bookID);
			st.setInt(2,  employeeID);
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
	public List<Book> getAllEmployeeBoks(int employeeID) throws DAOException {
		List<Book> books = new ArrayList<Book>();
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement(SELECT_BOOKS_BY_EMPLOYEE);
			st.setInt(1, employeeID);
			rs = st.executeQuery();
			
			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt(1));
				book.setTitle(rs.getString(2));
				book.setBrief(rs.getString(3));
				book.setYear((short)rs.getInt(4));
				book.setAuthor(rs.getString(5));
				books.add(book);
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
		return books;
	}

	@Override
	public String getReportForMoreThan(int howMany) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder("-----This report contains employees who have read more than " + howMany + " books------" + "\n");
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement(SELECT_EMPS_READ_MORE_THAN);
			st.setInt(1, howMany);
			rs = st.executeQuery();
			while (rs.next()) {
				sb.append(rs.getString(1) + ": " + rs.getInt(2) + "\n");
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
		
		return sb.toString();
	}

	@Override
	public String getReportLessOrEqualThan(int howMany) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder("-----This report contains employees who have read less than or equals to " + howMany + " book------" + "\n");
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement(SELECT_EMPS_READ_LESS_OR_EQUALS);
			st.setInt(1, howMany);
			rs = st.executeQuery();
			while (rs.next()) {
				sb.append(rs.getString(1) + ", " + rs.getDate(2) + ": " + rs.getString(3) + "\n");
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
		
		return sb.toString();
	}


}
