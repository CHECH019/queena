package com.cdevs.queena.restController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/login")
    public ResponseEntity<Employee> login(@RequestBody Map<String, Object>employeeMap) {
        String email = (String) employeeMap.get("email");
        String pass = (String) employeeMap.get("password");
        Employee c =service.validateEmployee(email, pass);
        return new ResponseEntity<Employee>(c, HttpStatus.OK) ;
    }
    
}