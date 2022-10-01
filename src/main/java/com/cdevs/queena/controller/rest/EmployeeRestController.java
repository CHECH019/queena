package com.cdevs.queena.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdevs.queena.commons.GenericServiceApi;
import com.cdevs.queena.model.Employee;
import com.cdevs.queena.service.api.EmployeeServiceAPI;

@RestController
@RequestMapping(value = GenericRestController.BASE_URL + "employee")
public class EmployeeRestController extends GenericRestController<Employee,Long>{
    
    @Autowired
    private EmployeeServiceAPI service;

    @Override
    public GenericServiceApi<Employee, Long> getService() {
        return service;
    }
}