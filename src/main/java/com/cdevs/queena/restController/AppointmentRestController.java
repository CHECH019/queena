package com.cdevs.queena.restController;

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

import com.cdevs.queena.commons.GenericServiceApi;
import com.cdevs.queena.model.Appointment;
import com.cdevs.queena.model.Employee;
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

    @Override
    @PostMapping("/save")
    public ResponseEntity<Appointment> save(@RequestBody Appointment appointment){
        Appointment a = null; 
        Employee e = appointment.getEmployee();
        List<Appointment> list = service.getByEmployeeId(e.getId());
        for(Appointment a1 : list){
            if(a1.getLdt().equals(appointment.getLdt())){
                System.out.println("HORARIO NO DISPONIBLE");
                return  new ResponseEntity<Appointment>(a, HttpStatus.OK);
            }
        }
        a = getService().save(appointment);
        return new ResponseEntity<Appointment>(a, HttpStatus.OK);
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