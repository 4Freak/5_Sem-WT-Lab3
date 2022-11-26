package by.BSUIR.WT.Lab3.server.dao;

import by.BSUIR.WT.Lab3.server.dao.impl.StudentCaseDAOImpl;

public class DAOFactory {
	
	private static final DAOFactory instance = new DAOFactory();
	
	private final StudentCaseDAOImpl studentCaseDAOImpl = StudentCaseDAOImpl.getInstance();
	
	private DAOFactory() {}
	
	public static DAOFactory getInstance(){
		return instance;
	}
	
	public StudentCaseDAOImpl getCaseDAO() {
		return studentCaseDAOImpl;
	}
	
}
