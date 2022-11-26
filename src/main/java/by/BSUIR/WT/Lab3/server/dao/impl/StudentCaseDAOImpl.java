package by.BSUIR.WT.Lab3.server.dao.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import by.BSUIR.WT.Lab3.server.dao.StudentCaseDAO;
import by.BSUIR.WT.Lab3.server.model.StudentCase;
import by.BSUIR.WT.Lab3.server.service.ServiceFactory;

public class StudentCaseDAOImpl implements StudentCaseDAO {

	private static final StudentCaseDAOImpl instance = new StudentCaseDAOImpl();
	private static final String path = "./src/resources/cases.xml";
	
	private final ReadWriteLock readWriteLock;
	private final Map<Integer, StudentCase> studentCases;
	
	
	private StudentCaseDAOImpl() {
		readWriteLock = new ReentrantReadWriteLock();
		studentCases = new HashMap<Integer, StudentCase>();
		LoadData();
	}
	
	private void LoadData() {
		
	}
	
	public static StudentCaseDAOImpl getInstance() {
		return instance;
	}
	
	@Override
	public boolean contains(int id) {
		return studentCases.containsKey(id);
	}
	
	@Override
	public List<StudentCase> getAll() {
		try {
			readWriteLock.readLock().lock();
			return new ArrayList<>(studentCases.values());
		}finally {
			readWriteLock.readLock().unlock();
		}
	}
	
	@Override
	public void add(StudentCase studentCase) {
		try {
			readWriteLock.writeLock().lock();
			studentCase.setId(studentCases.keySet().stream().max(Integer::compare).get()+1);
			studentCases.put(studentCase.getId(), studentCase);
			updateData();
		}finally {
			readWriteLock.writeLock().unlock();
		}
		
	}
	
	@Override
	public void setById(int id, StudentCase studentCase) {
		try {
			readWriteLock.writeLock().lock();
			studentCase.setId(id);
			studentCases.put(id, studentCase);
			updateData();
		} finally {
			readWriteLock.writeLock().unlock();
		}	
	}
	
	@Override
	public void updateData() {
		DocumentBuilderFactory  documentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			var documentBuilder = documentBuilderFactory.newDocumentBuilder();
			var document = documentBuilder.newDocument();
			var rootFile = document.createElement("cases");
			for (var studentCase : getAll()) {
				var caseElement = ServiceFactory.getInstance().getCaseService().createNode(document, studentCase);
				rootFile.appendChild(caseElement);
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
}
