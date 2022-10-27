package com.cdevs.queene.adminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdevs.queene.service.api.UserServiceAPI;

@Controller
@RequestMapping("/admin")
public class EmployeesController {
    @Autowired
    UserServiceAPI userService;

    @GetMapping("/empleados")
    public String list(Model model){
        model.addAttribute("employees", userService.getAll());
        return "employees/employees";
    }
}
