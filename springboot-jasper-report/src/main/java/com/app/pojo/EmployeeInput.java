package com.app.pojo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class EmployeeInput {
	
	@Id
	private int empId;
	
	private String firstName;
	
	private String midName;
	
	private String lastName;
	
	private LocalDate db;
	
	private LocalDate doj;
	
	private String empType;
	
	private String designation;

	public EmployeeInput() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeInput(int empId, String firstName, String midName, String lastName, LocalDate db, LocalDate doj,
			String empType, String designation) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.midName = midName;
		this.lastName = lastName;
		this.db = db;
		this.doj = doj;
		this.empType = empType;
		this.designation = designation;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMidName() {
		return midName;
	}

	public void setMidName(String midName) {
		this.midName = midName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDb() {
		return db;
	}

	public void setDb(LocalDate db) {
		this.db = db;
	}

	public LocalDate getDoj() {
		return doj;
	}

	public void setDoj(LocalDate doj) {
		this.doj = doj;
	}

	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	
}
