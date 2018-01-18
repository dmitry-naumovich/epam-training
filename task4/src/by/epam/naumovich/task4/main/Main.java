package by.epam.naumovich.task4.main;

import java.io.IOException;
import by.epam.naumovich.task4.view.View;

public class Main {
	public static void main(String[] args) throws Exception {
		try {
			View view = new View();
			view.doUserAction();
		} catch (IOException e) {
			throw new Exception(e);
		}
		
	}
}
