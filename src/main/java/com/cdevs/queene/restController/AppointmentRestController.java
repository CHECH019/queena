package com.cdevs.queene.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdevs.queene.generics.GenericRestController;
import com.cdevs.queene.generics.GenericServiceApi;
import com.cdevs.queene.global.Constants;
import com.cdevs.queene.model.Appointment;
import com.cdevs.queene.service.api.AppointmentServiceAPI;

@RestController
@RequestMapping(value = Constants.BASE_URL + "appointment")
public class AppointmentRestController extends GenericRestController<Appointment,Long>{

    @Autowired
    private AppointmentServiceAPI service;
    
    @Override
    public GenericServiceApi<Appointment, Long> getService() {
        return service;
    }
    
}