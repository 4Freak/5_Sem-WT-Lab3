package by.BSUIR.WT.Lab3.server.dao;

public class DAOFactory {
	
	private static final DAOFactory instance = new DAOFactory();
	
	private DAOFactory() {}
	
	public static DAOFactory getInstance(){
		return instance;
	}
	
}
