package by.epam.naumovich.task4.command;

import by.epam.naumovich.task4.domain.request.Request;
import by.epam.naumovich.task4.domain.response.Response;

public interface Command {
	
	Response execute(Request request);
}
