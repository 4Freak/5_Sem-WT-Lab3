package by.BSUIR.WT.Lab3.server.service.impl;

import java.util.List;

import javax.lang.model.element.Element;
import javax.swing.text.Document;

import org.w3c.dom.NodeList;

import by.BSUIR.WT.Lab3.server.model.StudentCase;
import by.BSUIR.WT.Lab3.server.service.CaseService;

public class CaseServiceImpl implements CaseService{

	private static final CaseServiceImpl instance = new CaseServiceImpl();
	
	@Override
	public StudentCase createCase(NodeList node) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Element createNode(Document document, StudentCase studentCase) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<StudentCase> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean containsCase(int id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void editCase(int id, String firstName, String lastName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCase(String firstName, String lastName) {
		// TODO Auto-generated method stub
		
	}
}
