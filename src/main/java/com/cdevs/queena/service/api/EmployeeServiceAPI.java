package com.cdevs.queena.service.api;

import com.cdevs.queena.commons.GenericServiceApi;
import com.cdevs.queena.exceptions.QAuthException;
import com.cdevs.queena.model.Employee;

public interface EmployeeServiceAPI extends GenericServiceApi<Employee, Long>{
    public Employee getByDni(Long id);
    public Employee getByEmail(String email) throws QAuthException;
    public Employee validateEmployee(String email, String password) throws QAuthException;
}
