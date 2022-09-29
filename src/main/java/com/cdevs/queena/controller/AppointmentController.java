package com.cdevs.queena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cdevs.queena.iServices.IAppointmentService;
import com.cdevs.queena.model.Appointment;

@Controller
public class AppointmentController {
    @Autowired
    private IAppointmentService service;

    @GetMapping("/appointments")
    public String show(Model model){
        model.addAttribute("appointments",service.getList());
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

    @GetMapping("/appointments/new")
    public String createAppointment(Model model){
        model.addAttribute("appointment", new Appointment());
        return "appointments/new-appointment";
    }

    @PostMapping("/appointments/save")
    public String saveAppointment(@ModelAttribute("appointment") Appointment appointment){
        service.save(appointment); 
        return "redirect:/appointments";
    }

    @GetMapping("/appointments/edit/{id}")
    public String editAppointment(@PathVariable long id, Model model){
        model.addAttribute("appointment", service.getById(id));
        return "appointments/edit-appointment";
    }

    @PostMapping("/appointments/{id}")
    public String updateAppointment(@PathVariable long id, @ModelAttribute("appointment") Appointment appointment){
        Appointment a = service.getById(id);
        a.setEmployee(appointment.getEmployee());
        a.setClient(appointment.getClient());
        a.setLdt(appointment.getLdt());
        a.setStatus(appointment.getStatus());
        service.update(a);
        return "redirect:/appointments";
    }

    @GetMapping("/appointments/{id}")
    public String deleteAppointment(@PathVariable long id){
        service.delete(id);
        return "redirect:/appointments";
    }
}
