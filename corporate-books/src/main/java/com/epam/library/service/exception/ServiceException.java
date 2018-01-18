package com.epam.library.service.exception;

/**
 * Describes superclass for all exceptions that may occur in service layer classes
 * 
 * @author Dmitry Naumovich
 * @version 1.0
 */
public class ServiceException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public ServiceException(String msg, Exception e) {
		super(msg, e);
	}

	public ServiceException(String msg) {
		super(msg);
	}
}
