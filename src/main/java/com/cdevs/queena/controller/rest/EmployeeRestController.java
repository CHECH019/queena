package com.cdevs.queena.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdevs.queena.model.Employee;
import com.cdevs.queena.service.api.EmployeeServiceAPI;

@RestController
@RequestMapping("api/v1/")
public class EmployeeRestController {
    @Autowired
    private EmployeeServiceAPI service;

    @GetMapping("/all-employees")
    public List<Employee> getAll(Model model){
        return service.getAll();
    }

    @PostMapping("/save-employee")
    public ResponseEntity<Employee> save(@RequestBody Employee employee){
        Employee e = service.save(employee); 
        return new ResponseEntity<Employee>(e, HttpStatus.OK);
    }

    @GetMapping("/find-employee/{id}")
    public ResponseEntity<Employee> get(@PathVariable Long id, Model model){
        Employee e = service.get(id);
        if(e != null)
            service.get(id);
        else
            return new ResponseEntity<Employee>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<Employee>(e,HttpStatus.OK);
    }

    @GetMapping("/delete-employee/{id}")
    public ResponseEntity<Employee> delete(@PathVariable Long id){
        Employee e = service.get(id);
        if(e != null)
            service.delete(id);
        else
            return new ResponseEntity<Employee>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<Employee>(e,HttpStatus.OK);
    }
}
