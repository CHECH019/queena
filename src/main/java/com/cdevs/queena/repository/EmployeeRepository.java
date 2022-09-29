package com.cdevs.queena.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdevs.queena.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {
    
}
