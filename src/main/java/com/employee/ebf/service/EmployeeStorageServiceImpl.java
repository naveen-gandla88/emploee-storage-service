package com.employee.ebf.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.employee.ebf.exceptions.ResourceNotFoundException;
import com.employee.ebf.model.Company;
import com.employee.ebf.model.EmployeeEntity;
import com.employee.ebf.repo.CompanyRepository;
import com.employee.ebf.repo.EmployeeRepository;

@Service
public class EmployeeStorageServiceImpl implements EmployeeStorageService{

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeRepository employeeRepo;

	@Autowired
	private CompanyRepository companyRepo;

	public List<EmployeeEntity> getEmpsForCompany(int companyId) {
		LOGGER.info("getEmployeesForCompany method called");
		List<EmployeeEntity> empList = employeeRepo.findByCompanyId(companyId);

		return empList;

	}

	public EmployeeEntity createEmployee(int companyid, EmployeeEntity employee) throws ResourceNotFoundException {
		LOGGER.info("createEmployee method called");
		return companyRepo.findById(companyid).map(company -> {
			employee.setCompany(company);
			return employeeRepo.save(employee);
		}).orElseThrow(() -> new ResourceNotFoundException("Company not found for id :: " + companyid));
	}

	public EmployeeEntity updateEmployee(int companyId, int employeeId, EmployeeEntity employee) throws ResourceNotFoundException {
		
		LOGGER.info("updateEmployee method called");
		if (!companyRepo.existsById(companyId)) {
			throw new ResourceNotFoundException("companyid not found");
		}
		return employeeRepo.findById(employeeId).map(emp -> {
			emp.setAddress(employee.getAddress());
			emp.setEmail(employee.getEmail());
			emp.setName(employee.getName());
			emp.setSalary(employee.getSalary());
			emp.setSurName(employee.getSurName());
			return employeeRepo.save(emp);
		}).orElseThrow(() -> new ResourceNotFoundException("Employee id not found"));
	}

	public void deleteEmployeeRecords(int employeeId,int companyId) throws ResourceNotFoundException {
		LOGGER.info("findByIdAndCompanyId method called");
		employeeRepo.findByIdAndCompanyId(employeeId, companyId).map(employee -> {
			employeeRepo.delete(employee);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException(
				"Employee not found with id " + employeeId));

	}

	public Double calculateEmployeeAvgSal(int companyid) throws ResourceNotFoundException {
		LOGGER.info("calculateEmployeeAvgSal method called");
		List<EmployeeEntity> empList = employeeRepo.findByCompanyId(companyid);
		if (empList.isEmpty()) {
			throw new ResourceNotFoundException("No Records found for the companyId :: " + companyid);
		}
		Double salary = empList.stream().mapToDouble(EmployeeEntity::getSalary).average().getAsDouble();
		return salary;
	}

	public EmployeeEntity getEmployee(int id) {
		LOGGER.info("getEmployee method called");
		Optional<EmployeeEntity> employee = employeeRepo.findById(id);
		return employee.get();
	}

	public Company getCompany(int id) throws ResourceNotFoundException {
		LOGGER.info("getCompany method called");
		Company comapny = companyRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(
				"No company found with id " + id));
		return comapny;
	}

	public List<Company> getCompanyDetail() { 
		LOGGER.info("getCompanyDetail method called");
		return companyRepo.findAll();
	}
	
	public List<EmployeeEntity> getAllCompanyEmps() {
		LOGGER.info("getAllCompanyEmps method called");
		List<EmployeeEntity> employees = employeeRepo.findAll();
		return employees;
	}

}
