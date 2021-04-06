package com.employee.ebf.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.ebf.exceptions.ResourceNotFoundException;
import com.employee.ebf.model.EmployeeEntity;
import com.employee.ebf.repo.CompanyRepository;
import com.employee.ebf.repo.EmployeeRepository;

@Service
public class EmployeeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeRepository employeeRepo;
	@Autowired
	private CompanyRepository companyRepo;

	
	public List<EmployeeEntity> getAllEmployees() {
		LOGGER.info("getAllEmployees method called");
		List<EmployeeEntity> empList = employeeRepo.findAll();
		return empList;

	}

	public EmployeeEntity createEmployee(EmployeeEntity employee) {
		LOGGER.info("createEmployee method called, employee: " + employee);
		employee = employeeRepo.save(employee);
		return employee;
	}

	public EmployeeEntity updateEmployee(int empid, EmployeeEntity employeeDtls) throws ResourceNotFoundException {
		LOGGER.info("updateEmployee method called, for employee Id: " + empid + " " + employeeDtls);
		EmployeeEntity emp = employeeRepo.findById(empid)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + empid));

		emp.setAddress(employeeDtls.getAddress());
		emp.setEmail(employeeDtls.getEmail());
		emp.setName(employeeDtls.getName());
		emp.setSurName(employeeDtls.getSurName());
		emp.setSalary(employeeDtls.getSalary());

		final EmployeeEntity updatedEmp = employeeRepo.save(emp);
		return updatedEmp;

	}

	public Map<String, Boolean> deleteEmployee(int empid) throws ResourceNotFoundException {
		LOGGER.info("deleteEmployee called, empid: " + empid);
		EmployeeEntity employee = employeeRepo.findById(empid)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + empid));

		employeeRepo.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return response;

	}

}
