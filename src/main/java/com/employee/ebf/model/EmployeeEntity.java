package com.employee.ebf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "employees",uniqueConstraints = { @UniqueConstraint(columnNames = { "company_id","name"}) })
public class EmployeeEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@ApiModelProperty(notes="Employee Id")
	@Column(name="id")
	private int id;
	@ApiModelProperty(notes="Employee Name")
	private String name;
	@ApiModelProperty(notes="Employee Surname")
	@Column(name = "emp_surname")
	private String surName;
	@ApiModelProperty(notes="Employee Email Address")
	@Column(name = "emp_email", nullable = false)
	private String email;
	@ApiModelProperty(notes="Employee Communication Address")
	@Column(name = "emp_address")
	private String address;
	@ApiModelProperty(notes="Employee Salary")
	@Column(name = "emp_salary")
	private Double salary;
	@ManyToOne
	@JoinColumn(name = "company_id")
	@JsonIgnore
	private Company company;

	public EmployeeEntity() {
	
	}
	public EmployeeEntity(String name, String surName, String email, String address, Double salary, Company company) {
		super();
		this.name = name;
		this.surName = surName;
		this.email = email;
		this.address = address;
		this.salary = salary;
		this.company = company;
	}




	/**
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surName
	 */
	public String getSurName() {
		return surName;
	}

	/**
	 * @param surName
	 *            the surName to set
	 */
	public void setSurName(String surName) {
		this.surName = surName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the salary
	 */
	public Double getSalary() {
		return salary;
	}

	/**
	 * @param salary
	 *            the salary to set
	 */
	public void setSalary(Double salary) {
		this.salary = salary;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", surName=" + surName + ", email=" + email + ", address="
				+ address + ", salary=" + salary + "]";
	}

}
