package by.BSUIR.WT.Lab3.server.command.impl;

import by.BSUIR.WT.Lab3.server.command.Command;
import by.BSUIR.WT.Lab3.server.command.exception.CommandExcpetion;
import by.BSUIR.WT.Lab3.server.model.AuthenticateType;
import by.BSUIR.WT.Lab3.server.service.ServiceFactory;

public class Edit implements Command{

	private final int argumentsCount = 4;
	
	@Override
	public String execute(Object caller, String request) throws CommandExcpetion {
		var arguments = request.split(" ");
		if (arguments.length != argumentsCount) {
			throw new CommandExcpetion(String.format("CREATE command should contain %d argument(s)", argumentsCount));
		}
		
		if (ServiceFactory.getInstance().getAuthenticateService().getAuthenticateType(caller) != AuthenticateType.MANAGER) {
			return "You should have manager rigths to execute this command";
		}
		
		int id;
		try {
			id = Integer.parseInt(arguments[1]);
		}catch (NumberFormatException e) {
			return "Invalid id";
		}
		
		if (!ServiceFactory.getInstance().getCaseService().containsCase(id)) {
			return "No such case";
		}
		
		ServiceFactory.getInstance().getCaseService().editCase(id, arguments[1], arguments[2]);
		return "Success!";
	}
}
