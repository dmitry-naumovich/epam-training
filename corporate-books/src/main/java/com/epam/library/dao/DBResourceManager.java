package com.epam.library.dao;

import java.util.ResourceBundle;

public class DBResourceManager {

	private static final DBResourceManager instance = new DBResourceManager();
	private static final String DB_PROPS_FILE = "db";
	private ResourceBundle bundle = ResourceBundle.getBundle(DB_PROPS_FILE);
	
	public static DBResourceManager getInstance() {
		return instance;
	}
	
	public String getValue(String key) {
		return bundle.getString(key);
	}
	
}
