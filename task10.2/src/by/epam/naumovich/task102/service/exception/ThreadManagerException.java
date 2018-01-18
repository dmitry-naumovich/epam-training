package by.epam.naumovich.task102.service.exception;

public class ThreadManagerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ThreadManagerException() {
		
	}
	
	public ThreadManagerException(String str) {
		super(str);
	}
	
	public ThreadManagerException(String str, Exception e) {
		super(str, e);
	}

}
