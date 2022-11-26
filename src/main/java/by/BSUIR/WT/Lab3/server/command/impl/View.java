package by.BSUIR.WT.Lab3.server.command.impl;

import java.util.List;

import by.BSUIR.WT.Lab3.server.command.Command;
import by.BSUIR.WT.Lab3.server.command.exception.CommandExcpetion;
import by.BSUIR.WT.Lab3.server.model.AuthenticateType;
import by.BSUIR.WT.Lab3.server.model.StudentCase;
import by.BSUIR.WT.Lab3.server.service.ServiceFactory;

public class View implements Command{
	
	@Override
	public String execute(Object caller, String request) throws CommandExcpetion {
		if (ServiceFactory.getInstance().getAuthenticateService().getAuthenticateType(caller) != AuthenticateType.UNAUTH) {
			return "You should be authenticated";
		}
		var studentCases = ServiceFactory.getInstance().getCaseService().getAll();
		return assembleOutput(studentCases);
	}
	
	public static String assembleOutput(List<StudentCase> studentCases) {
		var result = new StringBuilder();
		
		result.append("[\n");
		for (var studentCase : studentCases) {
			result.append("\t").append(studentCase.toString()).append("\n");
		}
		result.append("]");
		
		return result.toString();
	}
}
