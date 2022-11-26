package by.BSUIR.WT.Lab3.server.service.impl;

import java.util.HashMap;
import java.util.Map;

import by.BSUIR.WT.Lab3.server.model.AuthenticateType;
import by.BSUIR.WT.Lab3.server.service.AuthenticateService;

public class AuthenticateServiceImpl implements AuthenticateService {

	private static final AuthenticateServiceImpl instance = new AuthenticateServiceImpl();
	
	private final Map<Object, AuthenticateType> users = new HashMap<Object, AuthenticateType>();
	
	public AuthenticateServiceImpl() {}
	
	public static AuthenticateService getInstance() {
		return instance;
	}

	@Override
	public AuthenticateType getAuthenticateType(Object user) {
		AuthenticateType result;
		if (!users.containsKey(user)) {
			users.put(user, AuthenticateType.UNAUTH);
			result = AuthenticateType.UNAUTH;
		}else {			
			result = users.get(user);
		}
		return result;
	}
	
	@Override
	public void setAuthenticateType(Object user, AuthenticateType type) {
		users.put(user, type);
	}
}
