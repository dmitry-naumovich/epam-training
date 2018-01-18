package com.epam.library.controller;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.iface.IBookService;

public class MainController {

	public static final String CONTEXT_FILE_NAME = "spring-context.xml";
	public static final String SERVICE_BEAN_NAME = "bookService";
	public static final String SERVICE_ERROR_MSG = "Exception occured during service methods incovation. Sorry!";
	public static final String APP_ERROR = "Something went wrong! Sorry!";
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(CONTEXT_FILE_NAME);
		IBookService bookService = (IBookService)context.getBean(SERVICE_BEAN_NAME);
		try {
			System.out.println(bookService.getReportMoreThanOne());
			System.out.println(bookService.getReportLessOrEqualThanTwo());
			bookService.renameBook(1, "New Title!");
		} catch (ServiceException e) {
			System.err.println(SERVICE_ERROR_MSG);
		} catch (Exception e) {
			System.err.println(APP_ERROR);
		} finally {
			context.close();
		}
	}
}
