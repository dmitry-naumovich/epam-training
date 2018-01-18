package by.epam.naumovich.task4.controller;

import by.epam.naumovich.task4.command.Command;
import by.epam.naumovich.task4.domain.request.Request;
import by.epam.naumovich.task4.domain.response.Response;

public class Controller {

	private CommandHelper helper = new CommandHelper();
	
	public Response doAction(Request request) {
		String commandName = request.getCommandName();
		Command command = helper.getCommand(commandName);
		Response response = command.execute(request);
		return response;
	}
}
