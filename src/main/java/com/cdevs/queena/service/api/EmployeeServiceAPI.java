package com.cdevs.queena.service.api;

import com.cdevs.queena.model.Employee;

public interface EmployeeServiceAPI extends UserServiceApi<Employee>{
    public Employee getByDni(Long id);
}
