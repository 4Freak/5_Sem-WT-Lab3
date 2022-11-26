package by.BSUIR.WT.Lab3.server.service;

import by.BSUIR.WT.Lab3.server.model.AuthenticateType;

public interface AuthenticateService {

	public AuthenticateType getAuthenticateType(Object user);
	public void setAuthenticateType(Object user, AuthenticateType type);
}
