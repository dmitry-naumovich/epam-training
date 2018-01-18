package by.epam.naumovich.task83.dao;

import java.util.List;

import by.epam.naumovich.task83.dao.exception.DAOException;
import by.epam.naumovich.task83.domain.Review;

public interface IReviewDAO {

	void addReview(Review review) throws DAOException;
	void deleteReview(Review review) throws DAOException;
	List<Review> getAllReviews() throws DAOException;
	
	List<Review> getReviewsByUserId(int id) throws DAOException;
	List<Review> getReviewsByFilmId(int id) throws DAOException;
	
}
