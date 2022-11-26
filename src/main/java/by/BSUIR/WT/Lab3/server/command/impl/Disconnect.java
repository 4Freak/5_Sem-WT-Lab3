package by.BSUIR.WT.Lab3.server.command.impl;

import by.BSUIR.WT.Lab3.server.command.Command;
import by.BSUIR.WT.Lab3.server.model.AuthenticateType;
import by.BSUIR.WT.Lab3.server.service.ServiceFactory;

public class Disconnect implements Command{

	@Override
	public String execute(Object caller, String request) {
		ServiceFactory.getInstance().getAuthenticateService().setAuthenticateType(caller, AuthenticateType.UNAUTH);
		return "You have been disconnected";
	}
}
