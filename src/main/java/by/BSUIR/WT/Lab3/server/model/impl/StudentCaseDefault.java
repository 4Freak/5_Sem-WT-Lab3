package by.BSUIR.WT.Lab3.server.model.impl;

import java.util.Objects;

import by.BSUIR.WT.Lab3.server.model.StudentCase;

public class StudentCaseDefault implements StudentCase{

	private int id;
	private String firstName;
	private String lastName;
	
	public StudentCaseDefault(int id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	};

	@Override
	public String getFirstName() {
		return firstName;
	}
	
	
	@Override
	public String getLastName() {
		return lastName;
	}
	
	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		StudentCaseDefault studentCase = (StudentCaseDefault) o;
		return id == studentCase.id && firstName.equals(studentCase.firstName) && lastName.equals(studentCase.lastName);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, firstName, lastName);
	}
	
	@Override
	public String toString() {
		return "Student Case: {"+
				"\nId = " + id +
				"\nFirstName = " + firstName + 
				"\nLastName = " + lastName + 
				"\n}";
	}
}






