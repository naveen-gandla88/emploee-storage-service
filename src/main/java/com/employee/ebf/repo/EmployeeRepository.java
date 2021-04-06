package com.employee.ebf.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.ebf.model.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
	
	List<EmployeeEntity> findByCompanyId(int companyId);
	Optional<EmployeeEntity> findByIdAndCompanyId(int id, int companyId);
}
