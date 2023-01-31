package com.cdevs.queene.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cdevs.queene.generics.GenericServiceImpl;

@Service
public class EmployeeServiceImpl extends GenericServiceImpl<Employee,Long> implements EmployeeService {

    @Autowired
    private EmployeeDao dao;

    @Override
    public CrudRepository<Employee, Long> getDAO() {
        return dao;
    }

    
    
}
