package by.BSUIR.WT.Lab3.server.command;

import by.BSUIR.WT.Lab3.server.command.exception.CommandExcpetion;
import by.BSUIR.WT.Lab3.server.command.impl.Authenticate;
import by.BSUIR.WT.Lab3.server.command.impl.Create;
import by.BSUIR.WT.Lab3.server.command.impl.Disconnect;
import by.BSUIR.WT.Lab3.server.command.impl.Edit;
import by.BSUIR.WT.Lab3.server.command.impl.View;
import by.BSUIR.WT.Lab3.server.model.AuthenticateType;

public class CommandProvider {

	private static final CommandProvider instance = new CommandProvider();
	
	private final int  minArgumentsCount = 1;
	
	private CommandProvider() {}

	public static CommandProvider getInstance() {
		return instance;
	}
	
	public Command getCommand(String request) throws CommandExcpetion{
		if (request == null) {
			throw new CommandExcpetion("No command");
		}
        var arguments = request.split(" ");
        if (arguments.length < minArgumentsCount) {
        	throw new CommandExcpetion("No command");
        }
        
        return switch (arguments[0]) {
        	case "AUTH"			-> new Authenticate();
        	case "CREATE"		-> new Create();
        	case "DISCONNECT"	-> new Disconnect();
        	case "EDIT" 		-> new Edit();
        	case "VIEW"			-> new View();
        	default				-> throw new IllegalArgumentException("Unexpected value: " + arguments[0]);
        };	    
	}
}
