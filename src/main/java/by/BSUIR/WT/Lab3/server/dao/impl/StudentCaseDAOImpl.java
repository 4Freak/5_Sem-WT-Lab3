package by.BSUIR.WT.Lab3.server.dao.impl;

import java.io.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.BSUIR.WT.Lab3.server.dao.StudentCaseDAO;
import by.BSUIR.WT.Lab3.server.model.StudentCase;
import by.BSUIR.WT.Lab3.server.service.ServiceFactory;

public class StudentCaseDAOImpl implements StudentCaseDAO {

	private static final StudentCaseDAOImpl instance = new StudentCaseDAOImpl();
	private static final String path = ".\\src\\main\\resources\\cases.xml";//"./src/resources/cases.xml";
	
	private final ReadWriteLock readWriteLock;
	private final Map<Integer, StudentCase> studentCases;
	
	
	private StudentCaseDAOImpl() {
		readWriteLock = new ReentrantReadWriteLock();
		studentCases = new HashMap<Integer, StudentCase>();
		LoadData();
	}
	
	private void LoadData() {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(new File(path));
			document.getDocumentElement().normalize();
			NodeList nodes = document.getDocumentElement().getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					var studentCase = ServiceFactory.getInstance().getCaseService().createCase(node.getChildNodes());
					studentCases.put(studentCase.getId(), studentCase);
				}
			}
		}catch (ParserConfigurationException e){
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			Element rootFile = document.createElement("cases");
			for (var studentCase : getAll()) {
				Element caseElement = ServiceFactory.getInstance().getCaseService().createNode(document, studentCase);
				rootFile.appendChild(caseElement);
			}
			
			document.appendChild(rootFile);
			
			try {
				Transformer transformer = TransformerFactory.newInstance().newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.transform(new DOMSource(document), new StreamResult(new FileOutputStream(path)));
			}catch (IOException e) {
				e.printStackTrace();
			}catch (TransformerException e) {
				e.printStackTrace();
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
}
