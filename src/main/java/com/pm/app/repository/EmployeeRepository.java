package com.pm.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pm.app.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{
    
}
