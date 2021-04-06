package com.employee.ebf.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.ebf.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{
	
	

}
