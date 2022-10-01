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

import com.cdevs.queena.model.Appointment;
import com.cdevs.queena.service.api.AppointmentServiceAPI;

@RestController
@RequestMapping("api/v1/")
public class AppointmentRestController {
    @Autowired
    private AppointmentServiceAPI service;

    @GetMapping("/all-appointments")
    public List<Appointment> getAll(Model model){
        return service.getAll();
    }

    @PostMapping("/save-appointment")
    public ResponseEntity<Appointment> save(@RequestBody Appointment appointment){
        Appointment a = service.save(appointment); 
        return new ResponseEntity<Appointment>(a, HttpStatus.OK);
    }

    @GetMapping("/find-appointment/{id}")
    public ResponseEntity<Appointment> get(@PathVariable Long id, Model model){
        Appointment a = service.get(id);
        if(a != null)
            service.get(id);
        else
            return new ResponseEntity<Appointment>(a,HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<Appointment>(a,HttpStatus.OK);
    }

    @GetMapping("/delete-appointment/{id}")
    public ResponseEntity<Appointment> delete(@PathVariable Long id){
        Appointment a = service.get(id);
        if(a != null)
            service.delete(id);
        else
            return new ResponseEntity<Appointment>(a,HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<Appointment>(a,HttpStatus.OK);
    }
}