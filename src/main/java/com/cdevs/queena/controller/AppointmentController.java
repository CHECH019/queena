package com.cdevs.queena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cdevs.queena.model.Appointment;
import com.cdevs.queena.service.api.AppointmentServiceAPI;

@Controller
public class AppointmentController {
    @Autowired
    private AppointmentServiceAPI service;

    @GetMapping("/appointments")
    public String show(Model model){
        model.addAttribute("appointments",service.getAll());
        return "appointments/appointments";
    }
    
    @GetMapping("/appointments-by_employee_id")
    public String showByEmployeeId(Model model){
        model.addAttribute("users",service.getByEmployeeId(1020102192));
        return "appointments/appointments";
    }

    @GetMapping("/appointments-by_client_id")
    public String showByClientId(Model model){
        model.addAttribute("users",service.getByClientId(52552701));
        return "appointments/appointments";
    }

    
    @PostMapping("/appointments/save")
    public String save(@ModelAttribute("appointment") Appointment appointment){
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
        return "appointments/appointment-form";
    }

    @GetMapping("/appointments/{id}")
    public String deleteAppointment(@PathVariable long id){
        service.delete(id);
        return "redirect:/appointments";
    }
}
