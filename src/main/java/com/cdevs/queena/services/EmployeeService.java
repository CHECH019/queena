package com.cdevs.queena.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdevs.queena.iServices.IEmployeeService;
import com.cdevs.queena.model.Employee;
import com.cdevs.queena.repository.EmployeeRepository;

@Service
public class EmployeeService implements IEmployeeService{
    @Autowired
    private EmployeeRepository repository;

    @Override
    public Employee save(Employee e) {
        repository.save(e);
        return e;
    }

    @Override
    public List<Employee> getList() {
        return (List<Employee>) repository.findAll();
    }

    @Override
    public Employee getById(long id) {
        return repository.findById(id).get();
    }

    @Override
    public Employee update(Employee e) {
        return repository.save(e);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
    
}