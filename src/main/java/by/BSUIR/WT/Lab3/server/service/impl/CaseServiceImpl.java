package by.BSUIR.WT.Lab3.server.service.impl;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import by.BSUIR.WT.Lab3.server.dao.DAOFactory;
import by.BSUIR.WT.Lab3.server.model.StudentCase;
import by.BSUIR.WT.Lab3.server.model.impl.StudentCaseDefault;
import by.BSUIR.WT.Lab3.server.service.CaseService;

public class CaseServiceImpl implements CaseService{

	private static final CaseServiceImpl instance = new CaseServiceImpl();
	
	@Override
	public StudentCase createCase(NodeList nodes) {
		int id = 0;
		String firstName = "";
		String lastName = "";
		for (int i = 0; i < nodes.getLength(); i++) {
			var currNode = nodes.item(i);
			if (currNode.getNodeType() == Node.ELEMENT_NODE) {
				String text = currNode.getTextContent();
				System.out.println(currNode.getNodeName().toString());
				switch(currNode.getNodeName()) {
					case "id" 			-> id = Integer.parseInt(text);
					case "firstName" 	-> firstName = text;
					case "lastName"		-> lastName = text;
					case "case"			-> {}
					default 			-> throw new IllegalArgumentException("No case");
				}
			}
		}
		
		return new StudentCaseDefault(id, firstName, lastName);
	}
	
	@Override
	public Element createNode(Document document, StudentCase studentCase) {
		Element element = document.createElement("case");
		Element id = document.createElement("id");
		Element firstName = document.createElement("firstName");
		Element lastName = document.createElement("lastName");
		id.appendChild(document.createTextNode(String.valueOf(studentCase.getId())));
		firstName.appendChild(document.createTextNode(studentCase.getFirstName()));
		lastName.appendChild(document.createTextNode(studentCase.getLastName()));
		element.appendChild(id);
		element.appendChild(firstName);
		element.appendChild(lastName);
		return element;
	}
	
	@Override
	public List<StudentCase> getAll() {
		return DAOFactory.getInstance().getCaseDAO().getAll();
	}
	
	@Override
	public boolean containsCase(int id) {
		return DAOFactory.getInstance().getCaseDAO().contains(id);
	}
	
	@Override
	public void editCase(int id, String firstName, String lastName) {
		DAOFactory.getInstance().getCaseDAO().setById(id, new StudentCaseDefault(0, firstName, lastName));		
	}

	@Override
	public void addCase(String firstName, String lastName) {
		DAOFactory.getInstance().getCaseDAO().add(new StudentCaseDefault(0, firstName, lastName));
		
	}
}
