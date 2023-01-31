package com.cdevs.queene.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdevs.queene.model.Employee;

public interface EmployeeDaoAPI extends JpaRepository<Employee,Long> {
    
}
