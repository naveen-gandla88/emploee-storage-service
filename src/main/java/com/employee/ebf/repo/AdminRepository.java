package com.employee.ebf.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.ebf.model.AdminEntity;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Integer>{
	 public AdminEntity findByUsername(String username);
}
