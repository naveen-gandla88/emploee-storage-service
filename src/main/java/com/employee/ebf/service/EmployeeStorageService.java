package com.employee.ebf.service;

import java.util.List;

import com.employee.ebf.exceptions.ResourceNotFoundException;
import com.employee.ebf.model.Company;
import com.employee.ebf.model.EmployeeEntity;

public interface EmployeeStorageService {

	public List<EmployeeEntity> getEmpsForCompany(int companyId);

	public EmployeeEntity createEmployee(int companyid, EmployeeEntity employee) throws ResourceNotFoundException;

	public EmployeeEntity updateEmployee(int companyId, int employeeId, EmployeeEntity employee)
			throws ResourceNotFoundException;

	public void deleteEmployeeRecords(int employeeId, int companyId) throws ResourceNotFoundException;

	public Double calculateEmployeeAvgSal(int companyid) throws ResourceNotFoundException;

	public EmployeeEntity getEmployee(int id);

	public Company getCompany(int id) throws ResourceNotFoundException;

	public List<Company> getCompanyDetail();

	public List<EmployeeEntity> getAllCompanyEmps();

}
