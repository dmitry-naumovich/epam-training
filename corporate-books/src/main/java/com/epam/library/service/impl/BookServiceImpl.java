package com.epam.library.service.impl;

import com.epam.library.dao.exception.DAOException;
import com.epam.library.dao.iface.IBookDao;
import com.epam.library.domain.Book;
import com.epam.library.service.ExceptionMessages;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.iface.IBookService;

public class BookServiceImpl implements IBookService {

	private IBookDao bookDao;
	
	public void setBookDao(IBookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public String getReportMoreThanOne() throws ServiceException {
		try {
			return bookDao.getReportForMoreThan(1);
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
	}

	@Override
	public String getReportLessOrEqualThanTwo() throws ServiceException {
		try {
			return bookDao.getReportLessOrEqualThan(2);
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
	}

	@Override
	public void renameBook(int id, String newTitle) throws ServiceException {
		try {
			Book book = bookDao.getBookById(id);
			book.setTitle(newTitle);
			bookDao.updateBook(id, book);
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR);
		}
	}

}
