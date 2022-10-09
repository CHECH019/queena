package com.cdevs.queena.restController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdevs.queena.commons.GenericRestController;
import com.cdevs.queena.commons.GenericServiceApi;
import com.cdevs.queena.model.Appointment;
import com.cdevs.queena.service.api.AppointmentServiceAPI;
import com.cdevs.queena.service.api.ClientServiceAPI;
import com.cdevs.queena.constants.Constants;

@RestController
@RequestMapping(value = Constants.BASE_URL + "appointment")
public class AppointmentRestController extends GenericRestController<Appointment,Long>{

    @Autowired
    private AppointmentServiceAPI service;

    @Autowired
    private ClientServiceAPI clientService;
    
    @Override
    public GenericServiceApi<Appointment, Long> getService() {
        return service;
    }
    
    @PostMapping("/book")
    public ResponseEntity<Appointment> saveAppointment(@RequestBody Appointment entity, HttpServletRequest request){
        long id = (long) request.getAttribute("userID");
        entity.setClient(clientService.get(id));
        
        return super.save(entity);
    }
}