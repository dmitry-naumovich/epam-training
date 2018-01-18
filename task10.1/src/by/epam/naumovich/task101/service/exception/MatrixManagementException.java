package by.epam.naumovich.task101.service.exception;

public class MatrixManagementException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MatrixManagementException() {
		
	}
	
	public MatrixManagementException(String s) {
		super(s);
	}
	public MatrixManagementException(String s, Exception e) {
		super (s, e);
	}
}
