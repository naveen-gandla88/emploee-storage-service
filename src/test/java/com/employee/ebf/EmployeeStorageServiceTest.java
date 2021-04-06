package com.employee.ebf;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.employee.ebf.exceptions.ResourceNotFoundException;
import com.employee.ebf.model.Company;
import com.employee.ebf.model.EmployeeEntity;
import com.employee.ebf.repo.CompanyRepository;
import com.employee.ebf.repo.EmployeeRepository;
import com.employee.ebf.service.EmployeeStorageServiceImpl;

@SpringBootTest
public class EmployeeStorageServiceTest {

	@InjectMocks
	EmployeeStorageServiceImpl employeeStorageService;

	@Mock
	private EmployeeRepository employeeRepo;

	@Mock
	private CompanyRepository companyRepo;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getEmpsForCompanyTest() {
		List<EmployeeEntity> list = new ArrayList<EmployeeEntity>();
		Company company1 = new Company("Capgemini");

		EmployeeEntity empOne = new EmployeeEntity("NaveenKumar", "Gandla", "naveenkumar.g@capgemini.com",
				"Kochstrasse", new Double(900000), company1);
		//
		EmployeeEntity empThree = new EmployeeEntity("Martin", "Gandla", "naveenkumar.g@capgemini.com", "Kochstrasse",
				new Double(900000), company1);

		list.add(empOne);
		list.add(empThree);

		when(employeeStorageService.getEmpsForCompany(1)).thenReturn(list);
		// test
		List<EmployeeEntity> empList = employeeStorageService.getEmpsForCompany(1);
		assertEquals(2, empList.size());
		verify(employeeRepo, times(1)).findByCompanyId(1);
	}

	@Test
	public void calculateEmployeeAvgSalTest() throws ResourceNotFoundException {

		Company company2 = new Company("Ebf");
		EmployeeEntity empTwo = new EmployeeEntity("Alex", "Gandla", "naveenkumar.g@ebf.com", "Kochstrasse",
				new Double(50), company2);
		EmployeeEntity empThree = new EmployeeEntity("Alex", "Gandla", "naveenkumar.g@ebf.com", "Kochstrasse",
				new Double(100), company2);
		List<EmployeeEntity> list = new ArrayList<EmployeeEntity>();
		list.add(empTwo);
		list.add(empThree);
		when(employeeStorageService.calculateEmployeeAvgSal(1)).thenReturn(75.0);

		Double response = employeeStorageService.calculateEmployeeAvgSal(1);
		assertEquals(new Double(75.0), response);
		verify(employeeRepo, times(1)).findByCompanyId(1);
	}

	@Test
	public void getAllCompanyEmpsTest() {
		List<EmployeeEntity> list = new ArrayList<EmployeeEntity>();
		Company company1 = new Company("Capgemini");

		EmployeeEntity empOne = new EmployeeEntity("NaveenKumar", "Gandla", "naveenkumar.g@capgemini.com",
				"Kochstrasse", new Double(900000), company1);
		//
		EmployeeEntity empThree = new EmployeeEntity("Martin", "Gandla", "naveenkumar.g@capgemini.com", "Kochstrasse",
				new Double(900000), company1);

		list.add(empOne);
		list.add(empThree);
		when(employeeStorageService.getAllCompanyEmps()).thenReturn(list);
		List<EmployeeEntity> empList = employeeStorageService.getAllCompanyEmps();
		assertEquals(2, empList.size());
		verify(employeeRepo, times(1)).findAll();
	}

}
