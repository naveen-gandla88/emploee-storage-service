package com.employee.ebf.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.ebf.exceptions.ErrorDetails;
@Repository
public interface ErrorRepository extends JpaRepository<ErrorDetails, Integer>{

}
