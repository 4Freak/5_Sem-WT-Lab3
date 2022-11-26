package by.BSUIR.WT.Lab3.server.command.impl;

import by.BSUIR.WT.Lab3.server.command.Command;
import by.BSUIR.WT.Lab3.server.command.exception.CommandExcpetion;
import by.BSUIR.WT.Lab3.server.model.AuthenticateType;
import by.BSUIR.WT.Lab3.server.service.ServiceFactory;

public class Authenticate implements Command {
		
	private final int argumentsCount = 2;
	@Override
	public String execute(Object caller, String request) throws CommandExcpetion{
		var arguments = request.split(" ");
		if (arguments.length != argumentsCount) {
			throw new CommandExcpetion(String.format("AUTH command should contain %d argument(s)", argumentsCount));
		}
		AuthenticateType authenticateType;
		try {
			authenticateType = AuthenticateType.valueOf(arguments[1]);
		}catch (IllegalArgumentException e) {
			throw new CommandExcpetion("Incorrect authentication type");
		}
		
		ServiceFactory.getInstance().getAuthenticateService().setAuthenticateType(caller, authenticateType);
		
		return "Success!";
	}
}
