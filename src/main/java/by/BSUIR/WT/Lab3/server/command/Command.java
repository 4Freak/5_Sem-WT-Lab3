package by.BSUIR.WT.Lab3.server.command;

import by.BSUIR.WT.Lab3.server.command.exception.CommandExcpetion;

public interface Command {
	public String execute (Object caller, String request) throws CommandExcpetion;
}
