package by.BSUIR.WT.Lab3.server.dao;

import java.util.List;

import by.BSUIR.WT.Lab3.server.model.StudentCase;

public interface StudentCaseDAO {

	public boolean contains(int id);
	public List<StudentCase> getAll();
	public void add(StudentCase studentCase);
	public void setById(int id, StudentCase studentCase);
	public void updateData();
}