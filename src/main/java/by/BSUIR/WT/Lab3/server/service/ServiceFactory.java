package by.BSUIR.WT.Lab3.server.service;

import by.BSUIR.WT.Lab3.server.service.impl.*;

public class ServiceFactory {
	
	private static final ServiceFactory instance = new ServiceFactory();
	
	private final AuthenticateServiceImpl authenticationService = new AuthenticateServiceImpl();
	private final CaseServiceImpl caseServiceImpl = new CaseServiceImpl();
	
	private ServiceFactory() {}
	
	public static ServiceFactory getInstance() {
		return instance;
	}
	
	public AuthenticateServiceImpl getAuthenticateService() {
		return authenticationService;
	}
	
	public CaseServiceImpl getCaseService() {
		return caseServiceImpl;
	}
}
