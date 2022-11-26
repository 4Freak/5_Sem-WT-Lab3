package by.BSUIR.WT.Lab3.server.service;

import java.util.List;

import javax.lang.model.element.Element;
import javax.swing.text.Document;

import org.w3c.dom.NodeList;

import by.BSUIR.WT.Lab3.server.model.StudentCase;

public interface CaseService {

	public StudentCase createCase(NodeList node);
	public Element createNode(Document document, StudentCase studentCase);
	public List<StudentCase> getAll();
	public boolean containsCase(int id);
	public void editCase(int id, String firstName, String lastName);
	public void addCase(String firstName, String lastName);
}
