package com.cdevs.queena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdevs.queena.model.Employee;
import com.cdevs.queena.service.api.EmployeeServiceAPI;

@Controller
@RequestMapping
public class EmployeeController {
    @Autowired
    private EmployeeServiceAPI service;

    @GetMapping("/employees")
    public String show(Model model){
        model.addAttribute("employees", service.getAll());
        return "/employees/employees";
    }

    @GetMapping("/employees/new")
    public String create(Model model){
        model.addAttribute("employee", new Employee());
        return "/employees/new-employee";
    }

    @PostMapping("/employees/save")
    public String save(@ModelAttribute("employee") Employee employee){
        service.save(employee); 
        return "redirect:/employees";
    }

    @GetMapping({"/employees/edit/{id}","/employees/new/{id}"})
    public String showSave(@PathVariable Long id, Model model){
        if(id != null && id != 0){
            model.addAttribute("employee", service.get(id));
        }else{
            model.addAttribute("employee", new Employee());
        }
        return "/employees/employee-form";
    }

    @GetMapping("/employees/{id}")
    public String delete(@PathVariable long id){
        service.delete(id);
        return "redirect:/employees";
    }
}