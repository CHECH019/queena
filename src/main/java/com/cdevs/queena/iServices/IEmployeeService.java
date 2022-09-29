package com.cdevs.queena.iServices;

import java.util.List;

import com.cdevs.queena.model.Employee;

public interface IEmployeeService {
    public List<Employee> getList();
    public Employee getById(long id);
    public Employee save(Employee e);
    public void delete(long id);
    public Employee update(Employee e);
}
