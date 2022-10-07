package com.cdevs.queena.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdevs.queena.model.Appointment;
import com.cdevs.queena.model.Employee;
import com.cdevs.queena.service.api.AppointmentServiceAPI;
import com.cdevs.queena.service.api.ClientServiceAPI;
import com.cdevs.queena.service.api.EmployeeServiceAPI;

@Controller
@RequestMapping
public class AppointmentController {
    @Autowired
    private AppointmentServiceAPI service;

    @Autowired
    private EmployeeServiceAPI employeeService;

    @Autowired
    private ClientServiceAPI clientService;

    @GetMapping("/appointments")
    public String show(Model model){
        model.addAttribute("appointments",service.getAll());
        return "appointments/appointments";
    }
    
    @GetMapping("/appointments/client/{id}")
    public String showByClientId(@PathVariable Long id, Model model){
        model.addAttribute("appointments",service.getByClientId(id));
        return "appointments/appointments";
    }
    
    @GetMapping("/appointments/employee/{id}")
    public String showByEmployeeId(@PathVariable Long id, Model model){
        for(Appointment a: service.getByClientId(id)){
            System.out.println(a.getId());
        }
        model.addAttribute("appointments",service.getByEmployeeId(id));
        return "appointments/appointments";
    }

    @PostMapping("/appointments/save")
    public String save(@ModelAttribute("appointment") Appointment appointment){
        Employee e = appointment.getEmployee();
        List<Appointment> list = service.getByEmployeeId(e.getId());
        for(Appointment a : list){
            if(a.getLdt().equals(appointment.getLdt())){
                System.out.println("HORARIO NO DISPONIBLE");
                return "redirect:/appointments";
            }
        }
        service.save(appointment); 
        return "redirect:/appointments";
    }

    @GetMapping({"/appointments/edit/{id}","/appointments/new/{id}"})
    public String showSave(@PathVariable Long id, Model model){
        if(id != null && id != 0){ 
            model.addAttribute("appointment", service.get(id));
        }else{
            model.addAttribute("appointment", new Appointment());
        }
        model.addAttribute("employees", employeeService.getAll());
        return "appointments/appointment-form";
    }

    @GetMapping({"/appointments/new/client/{id}"})
    public String showSave2(@PathVariable Long id, Model model){
        Appointment a = new Appointment();
        a.setClient(clientService.get(id));
        model.addAttribute("appointment", a);
        model.addAttribute("employees", employeeService.getAll());
        return "appointments/appointment-form";
    }

    @GetMapping("/appointments/{id}")
    public String deleteAppointment(@PathVariable long id){
        service.delete(id);
        return "redirect:/appointments";
    }
}
