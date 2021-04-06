package com.employee.ebf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.employee.ebf.exceptions.ResourceNotFoundException;
import com.employee.ebf.model.Company;
import com.employee.ebf.model.EmployeeEntity;
import com.employee.ebf.service.EmployeeStorageServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="Employee Storage Service for the company")
@RestController
public class EmployeeStorageController {

	@Autowired
	private EmployeeStorageServiceImpl employeeStorageService;

	@ApiOperation(value="view all employee details for company")
	@GetMapping(value = "/employees/{companyid}")
	public List<EmployeeEntity> getEmployeesForCompany(@PathVariable(value = "companyid") int companyid) {
		List<EmployeeEntity> employees = employeeStorageService.getEmpsForCompany(companyid);
		return employees;
	}
	
	@ApiOperation(value="view all companies details")
	@GetMapping(value = "/companies",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Company> getCompanies() {
		List<Company> employees = employeeStorageService.getCompanyDetail();
		return employees;
	}

	@ApiOperation(value="view employee details")
	@GetMapping(value = "/company/{companyId}/employees/{id}")
	public EmployeeEntity getEmployee(@PathVariable(value = "id") int id, @PathVariable(value = "companyId") int companyId) {
		EmployeeEntity employee = employeeStorageService.getEmployee(id);
		return employee;
	}

	
	@ApiOperation(value="create employee records for company")
	@PostMapping(value = "/company/{companyId}/employees")
	public EmployeeEntity createEmployeeForCompany(@PathVariable(value = "companyId") int companyId,
			@RequestBody EmployeeEntity employee) throws ResourceNotFoundException {
		EmployeeEntity empl = employeeStorageService.createEmployee(companyId, employee);
		return empl;
	}

	@ApiOperation(value="update employee records for company")
	@PutMapping(value = "/company/{companyId}/{employeeId}")
	public EmployeeEntity updateEmployeeForCompany(@PathVariable(value = "companyId") int companyId,
			@PathVariable(value = "employeeId") int employeeId, @RequestBody EmployeeEntity employee)
			throws ResourceNotFoundException {
		EmployeeEntity empl = employeeStorageService.updateEmployee(companyId, employeeId, employee);
		return empl;

	}

	@ApiOperation(value="delete employee records for company")
	@DeleteMapping(value = "/company/{companyId}/employee/{employeeId}")
	public void deleteEmployee(@PathVariable(value = "employeeId") int employeeId,
			@PathVariable(value = "employeeId") int companyId) throws ResourceNotFoundException {
		employeeStorageService.deleteEmployeeRecords(employeeId,companyId);

	}
	
	@ApiOperation(value="view company details")
	@GetMapping(value = "/company/{id}")
	public Company getCompany(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
		Company company = employeeStorageService.getCompany(id);
		return company;
	}

	@ApiOperation(value="find average salary for company")
	@GetMapping(value = "/company/{companyid}/empsalary")
	public Double calculateEmployeeAvgSalary(@PathVariable(value = "companyid") int companyid)
			throws ResourceNotFoundException {
		Double salary = employeeStorageService.calculateEmployeeAvgSal(companyid);
		return salary;
	}

}
