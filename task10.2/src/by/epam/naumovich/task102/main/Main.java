package by.epam.naumovich.task102.main;

import by.epam.naumovich.task102.service.ThreadManager;
import by.epam.naumovich.task102.service.exception.ThreadManagerException;

public class Main {

	public static void main(String[] args) {
		ThreadManager tManager = new ThreadManager(args[0]);
		try {
			tManager.manage();
		} catch (ThreadManagerException e) {
			// logging
			e.printStackTrace();
		}
		
	}

}
