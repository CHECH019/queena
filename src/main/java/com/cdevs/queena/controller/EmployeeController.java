package com.cdevs.queena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdevs.queena.iServices.IEmployeeService;
import com.cdevs.queena.model.Employee;

@Controller
@RequestMapping
public class EmployeeController {
    @Autowired
    private IEmployeeService service;

    @GetMapping("/employees")
    public String show(Model model){
        model.addAttribute("employees", service.getList());
        return "/employees/employees";
    }

    @GetMapping("/employees/new")
    public String createEmployee(Model model){
        model.addAttribute("employee", new Employee());
        return "/employees/new-employee";
    }

    @PostMapping("/employees/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        service.save(employee); 
        return "redirect:/employees";
    }

    @GetMapping("/employees/edit/{id}")
    public String editEmployee(@PathVariable long id, Model model){
        model.addAttribute("employee", service.getById(id));
        return "/employees/edit-employee";
    }

    @PostMapping("/employees/{id}")
    public String updateEmployee(@PathVariable long id, @ModelAttribute("employee") Employee employee){
        Employee e = service.getById(id);
        e.setName(employee.getName());
        e.setSurname(employee.getSurname());
        e.setPhoneNumber(employee.getPhoneNumber());
        e.setPassword(employee.getPassword());
        e.setAddress(employee.getAddress());

        service.update(e);
        return "redirect:/employees";
    }

    @GetMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable long id){
        service.delete(id);
        return "redirect:/employees";
    }
}