package by.BSUIR.WT.Lab3.server.command.impl;

import by.BSUIR.WT.Lab3.server.command.Command;
import by.BSUIR.WT.Lab3.server.command.exception.CommandExcpetion;
import by.BSUIR.WT.Lab3.server.model.AuthenticateType;
import by.BSUIR.WT.Lab3.server.service.ServiceFactory;

public class Create implements Command{
	
	private final int argumentsCount = 3;
	
	@Override
	public String execute(Object caller, String request) throws CommandExcpetion {
		var arguments = request.split(" ");
		if (arguments.length != argumentsCount) {
			throw new CommandExcpetion(String.format("CREATE command should contain %d argument(s)", argumentsCount));
		}
		
		if (ServiceFactory.getInstance().getAuthenticateService().getAuthenticateType(caller) != AuthenticateType.MANAGER) {
			return "You should have manager rigths to execute this command";
		}
		
		ServiceFactory.getInstance().getCaseService().addCase(arguments[1], arguments[2]);
		return "Success!";
	}
}
