package com.employee.ebf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import com.employee.ebf.exceptions.ErrorDetails;
import com.employee.ebf.exceptions.ResourceNotFoundException;
import com.employee.ebf.model.AdminEntity;
import com.employee.ebf.model.EmployeeEntity;
import com.employee.ebf.model.MainFormModel;
import com.employee.ebf.repo.AdminRepository;
import com.employee.ebf.repo.ErrorRepository;

import static com.employee.ebf.config.Constants.*;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

public class EmployeeStorageViewController extends EmployeeStorageController{
	private static final String RESULT = "result";

	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private ErrorRepository errorRepository;

	@RequestMapping(LIST_URL)
	public String handleConverterForm(MainFormModel mainFormModel, Model model) {
		return getListFrom(mainFormModel.getCompany(), model);
	}

	private String getListFrom(int companyId, Model model) {
		try {
			model.addAttribute("companies", getCompanies());
			model.addAttribute("employees", getEmployeesForCompany(companyId));
			model.addAttribute(RESULT, calculateEmployeeAvgSalary(companyId));
			return LIST_URL;
		} catch (Exception exp) {
			model.addAttribute(RESULT, "");
			model.addAttribute("error", exp.getMessage());
			return LIST_URL;
		}
	}

	@RequestMapping(EDIT_URL)
	public String handleEditForm(@RequestParam(value = "id", defaultValue = "-1") int id,
			@RequestParam(value = "companyId", defaultValue = "-1") int companyId, EmployeeEntity entity,
			HttpServletResponse httpServletResponse) throws ResourceNotFoundException {
		if (entity != null && entity.getName() != null && !entity.getName().isEmpty() && entity.getSurName() != null
				&& !entity.getSurName().isEmpty()) {
			updateEmployeeForCompany(id, companyId, entity);
			httpServletResponse.setHeader("Location", "/employees");
		} else if (id >= 0) {
			EmployeeEntity fromDB = getEmployee(id, companyId);
			if (entity != null) {
				entity.setName(fromDB.getName());
				entity.setSurName(fromDB.getSurName());
				entity.setEmail(fromDB.getEmail());
				entity.setAddress(fromDB.getAddress());
				entity.setSalary(fromDB.getSalary());
				entity.setId(id);
				entity.setCompany(fromDB.getCompany());
			}
		} else {
			if (entity != null) {
				entity.setCompany(getCompany(companyId));
			}
		}
		return EDIT_URL;
	}

	@RequestMapping(DELETE_URL)
	public String handleDeleteForm(@RequestParam(value = "id", defaultValue = "-1") int id,
			@RequestParam(value = "companyId", defaultValue = "-1") int companyId, MainFormModel mainFormModel,
			Model model) throws ResourceNotFoundException {
		deleteEmployee(id, companyId);
		return DELETE_URL;
	}

	@RequestMapping(REGISTER_URL)
	public String handleRegisterForm(AdminEntity adminEntity, Model model) {
		try {
			if (adminEntity.getUsername() != null && !adminEntity.getUsername().isEmpty()) {
				if (adminRepository.findByUsername(adminEntity.getUsername()) != null) {
					model.addAttribute("error", "This UserName already used");

					return REGISTER_URL;
				} else {
					adminRepository.save(adminEntity);
					return LOGIN_URL;
				}
			} else {

				return REGISTER_URL;
			}
		} catch (Exception exp) {
			model.addAttribute("error", exp.getMessage());
			return REGISTER_URL;
		}
	}

	
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName(LOGIN_URL);
		registry.addViewController("/" + LOGIN_URL).setViewName(LOGIN_URL);
	}

	@ExceptionHandler(Exception.class)
	public String handleError(Model model, Exception ex) {
		errorRepository.save(new ErrorDetails(new Date(), "Error in service", ex.getMessage()));
		model.addAttribute("error", ex.getMessage());
		return LIST_URL;
	}

}
