package com.epam.library.dao;

/**
 * Defines a set of String constants that describe occurred exceptions in the DAO layer classes
 * 
 * @author Dmitry Naumovich
 * @version 1.0
 */
public final class ExceptionMessages {
	
	public static final String CONNECTION_NOT_ADDED = "Error while allocating the first connection in the pool.";
	public static final String CONNECTION_NOT_ALLOCATED = "Error while allocating the connection in the pool. No space currently available!";
	public static final String CONNECTION_NOT_FOUND = "Error! Connection was not found in the connection pool";
	public static final String CONNECTION_NOT_TAKEN = "Failure during taking connection from the Connection Pool";
	public static final String CONNECTION_NOT_CLOSED = "Connection was not closed and returned to pool";
	public static final String IMPOSSIBLE_TO_CLOSE = "It is impossible to close the connection which is already closed.";
	public static final String INVALID_INITIALIZATION_PARAMETER = "Invalid connection pool initialization parameter! Please, check the properties file.";
	public static final String PREP_STATEMENT_NOT_CLOSED = "Prepared Statement was not closed properly";
	public static final String RS_OR_STATEMENT_NOT_CLOSED = "Result Set or Statement was not closed properly";
	public static final String SQL_DELETE_FAILURE = "Failure during the SQL delete request execution";
	public static final String SQL_EXCEPTION_IN_POOL = "SQLException happened in ConnectionPool";
	public static final String SQL_INSERT_FAILURE = "Failure during the SQL insert request execution";
	public static final String SQL_SELECT_FAILURE = "Failure during the SQL select request execution";
	public static final String SQL_SHOW_FAILURE = "Failure during the SQL show request execution";
	public static final String SQL_UPDATE_FAILURE = "Failure during the SQL update request execution";
	public static final String UNKNOWN_DATA_SOURCE = "Unknown data source type. Please, try again.";
	
	private ExceptionMessages() {}
}
