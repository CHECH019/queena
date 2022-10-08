package com.cdevs.queena.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdevs.queena.commons.GenericServiceApi;
import com.cdevs.queena.model.Appointment;
import com.cdevs.queena.service.api.AppointmentServiceAPI;

@RestController
@RequestMapping(value = GenericRestController.BASE_URL + "appointment")
public class AppointmentRestController extends GenericRestController<Appointment,Long>{

    @Autowired
    private AppointmentServiceAPI service;
    
    @Override
    public GenericServiceApi<Appointment, Long> getService() {
        return service;
    }


    @GetMapping("/all/client-{id}")
    public List<Appointment> showByClientId(@PathVariable Long id, Model model){
        return service.getByClientId(id);
    }
    
    @GetMapping("/all/employee-{id}")
    public List<Appointment> showByEmployeeId(@PathVariable Long id, Model model){
        return service.getByEmployeeId(id);
    }
}