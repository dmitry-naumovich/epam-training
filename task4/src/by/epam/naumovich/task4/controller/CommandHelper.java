package by.epam.naumovich.task4.controller;

import java.util.HashMap;
import java.util.Map;

import by.epam.naumovich.task4.command.Command;
import by.epam.naumovich.task4.command.impl.CreateXMLWithStructureCommand;
import by.epam.naumovich.task4.command.impl.FindNewsCommand;
import by.epam.naumovich.task4.command.impl.GetCatalogCommand;
import by.epam.naumovich.task4.command.impl.SaveNewNewsCommand;

public class CommandHelper {

	private Map<CommandName, Command> commands = new HashMap<>();
	
	public CommandHelper() {
		commands.put(CommandName.SAVE_NEW_NEWS, new SaveNewNewsCommand());
		commands.put(CommandName.FIND_NEWS, new FindNewsCommand());
		commands.put(CommandName.GET_CATALOG, new GetCatalogCommand());
		commands.put(CommandName.CREATE_XML_WITH_STRUCTURE, new CreateXMLWithStructureCommand());
	}
	
	public Command getCommand(String name) {
		CommandName commandName = CommandName.valueOf(name);
		Command command = commands.get(commandName);
		return command;
	}
}
