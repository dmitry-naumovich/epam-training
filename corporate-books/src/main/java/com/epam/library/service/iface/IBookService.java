package com.epam.library.service.iface;

import com.epam.library.service.exception.ServiceException;

public interface IBookService {

	String getReportMoreThanOne() throws ServiceException;
	String getReportLessOrEqualThanTwo() throws ServiceException;
	
	void renameBook(int id, String newTitle) throws ServiceException;
}
