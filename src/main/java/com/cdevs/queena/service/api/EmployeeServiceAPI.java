package com.cdevs.queena.service.api;

import com.cdevs.queena.model.Employee;

public interface EmployeeServiceAPI extends UserServiceAPI<Employee>{
    public Employee getByDni(Long id);
}
