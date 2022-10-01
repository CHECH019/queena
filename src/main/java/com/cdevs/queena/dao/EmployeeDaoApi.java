package com.cdevs.queena.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdevs.queena.model.Employee;

@Repository
public interface EmployeeDaoApi extends CrudRepository<Employee,Long> {
    
}
