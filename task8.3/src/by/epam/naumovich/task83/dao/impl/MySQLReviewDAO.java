package by.epam.naumovich.task83.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.naumovich.task82.dao.pool.MySQLConnectionPool;
import by.epam.naumovich.task82.dao.pool.exception.ConnectionPoolException;
import by.epam.naumovich.task83.dao.IReviewDAO;
import by.epam.naumovich.task83.dao.exception.DAOException;
import by.epam.naumovich.task83.domain.Review;

public class MySQLReviewDAO implements IReviewDAO {

	private static final MySQLReviewDAO instance = new MySQLReviewDAO();
	
	public static final String INSERT_NEW_REVIEW = "INSERT INTO Reviews (r_author, r_film, r_date, r_type, r_mark, r_text) VALUES (?, ?, ?, ?, ?, ?)";
	public static final String DELETE_REVIEW = "DELETE FROM Reviews WHERE Reviews.r_author = ? and Reviews.r_film = ?";
	public static final String SELECT_ALL_REVIEWS = "SELECT * FROM Reviews";
	public static final String SELECT_REVIEWS_BY_USER_ID = "SELECT * FROM Reviews WHERE Reviews.r_author = ?";
	public static final String SELECT_REVIEWS_BY_FILM_ID = "SELECT * FROM Reviews WHERE Reviews.r_film = ?";
	public static final String UPDATE_FILM_RATING = "UPDATE Films SET f_rating = (SELECT AVG(r_mark) FROM Reviews WHERE Films.f_id = Reviews.r_film) WHERE (SELECT COUNT(r_mark) FROM Reviews WHERE Films.f_id = Reviews.r_film) > 0;";
	
	public static MySQLReviewDAO getInstance() {
		return instance;
	}

	@Override
	public void addReview(Review review) throws DAOException {
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		PreparedStatement stForRatingUpdate = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(INSERT_NEW_REVIEW);
			st.setInt(1, review.getAuthor());
			st.setInt(2, review.getFilmId());
			st.setDate(3, review.getDate());
			st.setString(4, review.getType());
			st.setInt(5, review.getMark());
			st.setString(6, review.getText());
			st.executeUpdate();
			
			stForRatingUpdate = con.prepareStatement(UPDATE_FILM_RATING);
			stForRatingUpdate.executeUpdate();
			
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
	public void deleteReview(Review review) throws DAOException {
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		PreparedStatement stForRatingUpdate = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(DELETE_REVIEW);
			st.setString(1, String.valueOf(review.getAuthor()));
			st.setString(2, String.valueOf(review.getFilmId()));
			st.executeUpdate();
			
			stForRatingUpdate = con.prepareStatement(UPDATE_FILM_RATING);
			stForRatingUpdate.executeUpdate();
			
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
	
	@Override
	public List<Review> getAllReviews() throws DAOException {
		List<Review> reviewList = new ArrayList<Review>();
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(SELECT_ALL_REVIEWS);
			rs = st.executeQuery();
			
			while (rs.next()) {
				Review review = new Review();
				review.setAuthor(rs.getInt(1));
				review.setFilmId(rs.getInt(2));
				review.setDate(rs.getDate(3));
				review.setType(rs.getString(4));
				review.setMark(rs.getInt(5));
				review.setText(rs.getString(6));
				
				reviewList.add(review);
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
		return reviewList;
	}

	@Override
	public List<Review> getReviewsByUserId(int id) throws DAOException {
		List<Review> reviewList = new ArrayList<Review>();
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(SELECT_REVIEWS_BY_USER_ID);
			st.setString(1, String.valueOf(id));
			rs = st.executeQuery();
			
			while (rs.next()) {
				Review review = new Review();
				review.setAuthor(rs.getInt(1));
				review.setFilmId(rs.getInt(2));
				review.setDate(rs.getDate(3));
				review.setType(rs.getString(4));
				review.setMark(rs.getInt(5));
				review.setText(rs.getString(6));
	
				reviewList.add(review);
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
		return reviewList;
	}

	@Override
	public List<Review> getReviewsByFilmId(int id) throws DAOException {
		List<Review> reviewList = new ArrayList<Review>();
		MySQLConnectionPool pool = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			pool = MySQLConnectionPool.getInstance();
			con = pool.getConnection();
			st = con.prepareStatement(SELECT_REVIEWS_BY_FILM_ID);
			st.setString(1, String.valueOf(id));
			rs = st.executeQuery();
			
			while (rs.next()) {
				Review review = new Review();
				review.setAuthor(rs.getInt(1));
				review.setFilmId(rs.getInt(2));
				review.setDate(rs.getDate(3));
				review.setType(rs.getString(4));
				review.setMark(rs.getInt(5));
				review.setText(rs.getString(6));
	
				reviewList.add(review);
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
		return reviewList;
	}
	
}
